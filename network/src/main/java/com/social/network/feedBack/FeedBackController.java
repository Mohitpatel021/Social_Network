package com.social.network.feedBack;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "FeedBack", description = "FeedBack")
public class FeedBackController {

    private final FeedbackService service;

    @PostMapping()
    public ResponseEntity<?> saveFeedBack(@Valid @RequestBody FeedBackRequest request, Authentication connectedUser) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/{feedback-id}")
    public ResponseEntity<?> findFeedbackById(@PathVariable("feedback-id") Integer feedbackId, Authentication connectedUser) {
        return ResponseEntity.ok(service.findFeedbackById(feedbackId));
    }

    @GetMapping("/book/{book-id}")
    public ResponseEntity<?> findAllFeedbackByBook(@PathVariable("book-id") Integer bookId,
                                                   @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                   @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                                   Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.findAllFeedbacksByBook(bookId, page, size, connectedUser));
    }
}
