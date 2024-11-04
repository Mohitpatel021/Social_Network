package com.social.network.feedBack;

import com.social.network.book.Book;
import com.social.network.book.BookRepository;
import com.social.network.exceptions.OperationNotPermittedException;
import com.social.network.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final BookRepository bookRepository;
    private final FeedBackMapper feedbackMapper;
    private final FeedBackRepository feedbackRepository;
    public Integer save(FeedBackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId()).orElseThrow(() -> new EntityNotFoundException("Book not found :: " + request.bookId()));
        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give a Feedback for an archived or not shareable book :: " + book.getTitle());
        }
        User user = ((User) connectedUser.getPrincipal());
        if (!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give a feedback to your own book :: " + book.getTitle());
        }

        Feedback feedback=feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }
}
