package bahlwan.library.homework.services;

import bahlwan.library.homework.dtos.AuthorRequest;
import bahlwan.library.homework.models.Author;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AuthorService {
    Author getAuthor(Long id);
    List<Author> getAllAuthors(Pageable pageable);
    Author create(AuthorRequest request);
    Author update(Long id, AuthorRequest request);
    void delete(Long id);
}
