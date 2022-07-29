package bahlwan.library.homework.services;

import bahlwan.library.homework.graphql.converters.AuthorToAuthorResponseConverter;
import bahlwan.library.homework.dtos.AuthorRequest;
import bahlwan.library.homework.dtos.AuthorResponse;
import bahlwan.library.homework.models.Author;
import bahlwan.library.homework.models.Book;
import bahlwan.library.homework.repositories.AuthorRepository;
import bahlwan.library.homework.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    AuthorToAuthorResponseConverter converter;

    public AuthorResponse getAuthor(String id) {
        return converter.convert(authorRepository.findAuthorById(id));
    }

    public List<AuthorResponse> getAllAuthors(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return converter.convertAll(authors.getContent());
    }

    public AuthorResponse create(AuthorRequest request) {
        Author author = new Author();
        this.prepare(author,request);
        return converter.convert(authorRepository.save(author));
    }

    public AuthorResponse update(String id, AuthorRequest request){
        Author author = authorRepository.getById(id);
        return converter.convert(this.prepare(author,request));
    }

    @Override
    public List<Author> allAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author author(String id) {
        return authorRepository.findAuthorById(id);
    }

    public void delete(String id){
        authorRepository.deleteById(id);
    }

    private Author prepare(Author author,AuthorRequest request) {
        author.setName(request.getName());
        author.setBirthDate(request.getBirthDate());
        Set<Book> books = new HashSet<>(bookRepository.findAllById(request.getBooks()));
        author.setBooks(books);
        return author;
    }
}
