package com.social.network.service;


import com.social.network.repository.RoleRepository;
import com.social.network.repository.TokenRepository;
import com.social.network.repository.UserRepository;
import com.social.network.requests.AuthenticationRequest;
import com.social.network.requests.RegistrationRequest;
import com.social.network.response.AuthenticationResponse;
import com.social.network.security.JwtService;
import com.social.network.service.enums.EmailTemplateName;
import com.social.network.user.Token;
import com.social.network.user.User;
import com.social.network.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public void register(RegistrationRequest request) {
        var userRole = roleRepository.findByName("USER").orElseThrow(() -> new IllegalArgumentException("Role User was not initialized"));
        var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).accountLocked(false).enabled(false).roles(List.of(userRole)).build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

         private void sendValidationEmail(User user) {
                var newToken = generateAndSaveActivationToken(user);
                try {
                    emailService.sendEmail(user.getEmail(), user.getFullName(), EmailTemplateName.ACTIVATE_ACCOUNT, AppConstants.ACTIVATION_URL, newToken, "Activate Your Account");
                } catch (Exception e) {
                    throw new RuntimeException("Error While Sending the Email", e);
        }
    }

    private String generateAndSaveActivationToken(User user) {
        String generateToken = generateActivationCode(AppConstants.ACTIVATION_CODE_LENGTH);
        var token = Token.builder().token(generateToken).createdAt(LocalDateTime.now()).expiresAt(LocalDateTime.now().plusMinutes(15)).user(user).build();
        tokenRepository.save(token);
        return generateToken;
    }

    private String generateActivationCode(int codeLength) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getFullName());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Transactional
    public void activateAccount(String token) {
        Token savedToken=tokenRepository.findByToken(token)
                .orElseThrow(()->new RuntimeException("Invalid Token"));
        if(LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent to the same email address");
        }
        var user=userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(()->new RuntimeException("Invalid User"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

}
