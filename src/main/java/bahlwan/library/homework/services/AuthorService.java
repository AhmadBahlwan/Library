package bahlwan.library.homework.services;

import bahlwan.library.homework.dtos.AuthorRequest;
import bahlwan.library.homework.dtos.AuthorResponse;
import bahlwan.library.homework.models.Author;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AuthorService {
    AuthorResponse getAuthor(String id);
    List<AuthorResponse> getAllAuthors(Pageable pageable);
    AuthorResponse create(AuthorRequest request);
    AuthorResponse update(String id, AuthorRequest request);
    List<Author> allAuthors();
    Author author(String id);
    void delete(String id);
}
