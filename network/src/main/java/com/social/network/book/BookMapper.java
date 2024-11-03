package com.social.network.book;

import com.social.network.history.BookTransactionHistory;
import com.social.network.utils.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book toBook(BookRequest request) {
        return Book.builder().title(request.title()).authorName(request.authorName()).isbn(request.isbn()).synopsis(request.synopsis()).archived(request.isArchived()).shareable(request.shareable()).build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()

                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .owner(book.getOwner().getFullName())
                .rate(book.getRate())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .cover(FileUtils.readFileFromLocation(book.getBookCover()))
                .build();
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {

        return BorrowedBookResponse.builder()
                .id(history.getBook().getId())
                .title(history.getBook().getTitle())
                .authorName(history.getBook().getAuthorName())
                .isbn(history.getBook().getIsbn())
                .rate(history.getBook().getRate())
                .archived(history.getBook().isArchived())
                .returnApproved(history.isReturnApproved())
                .build();
    }
}
