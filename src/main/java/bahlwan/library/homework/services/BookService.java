package bahlwan.library.homework.services;

import bahlwan.library.homework.dtos.BookRequest;
import bahlwan.library.homework.dtos.BookResponse;
import bahlwan.library.homework.models.Book;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BookService {
     BookResponse getBook(String id);
     List<BookResponse> getAllBooks(Pageable pageable);
     List<Book>allBooks();
     Book getBookById(String id);
     BookResponse create(BookRequest request);
     BookResponse update(String id, BookRequest request);
     void delete(String id);
}
