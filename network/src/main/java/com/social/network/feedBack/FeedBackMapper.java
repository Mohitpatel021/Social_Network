package com.social.network.feedBack;

import com.social.network.book.Book;

public class FeedBackMapper {

    public Feedback toFeedback(FeedBackRequest request) {
        return Feedback.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                        .id(request.bookId())
                        .archived(false)
                        .shareable(false)
                        .build())
                .build();
    }
}
