package com.social.network.feedBack;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feedBacks")
@RequiredArgsConstructor
@Tag(name = "FeedBack", description = "FeedBack")
public class FeedBackController {

    private final FeedbackService service;
    @PostMapping()
    public ResponseEntity<?>saveFeedBack(@Valid @RequestBody FeedBackRequest request, Authentication connectedUser){
        return ResponseEntity.ok(service.save(request, connectedUser));
    }
}
