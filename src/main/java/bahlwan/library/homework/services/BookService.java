package bahlwan.library.homework.services;

import bahlwan.library.homework.dtos.BookRequest;
import bahlwan.library.homework.models.Book;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BookService {
     Book getBook(Long id);
     List<Book> getAllBooks(Pageable pageable);
     Book create(BookRequest request);
     Book update(Long id, BookRequest request);
     void delete(Long id);
}
