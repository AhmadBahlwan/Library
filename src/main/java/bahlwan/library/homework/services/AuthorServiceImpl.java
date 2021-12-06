package bahlwan.library.homework.services;

import bahlwan.library.homework.dtos.AuthorRequest;
import bahlwan.library.homework.models.Author;
import bahlwan.library.homework.models.Book;
import bahlwan.library.homework.repositories.AuthorRepository;
import bahlwan.library.homework.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Author getAuthor(Long id) {
        return authorRepository.findAuthorById(id);
    }

    public List<Author> getAllAuthors(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.getContent();
    }

    public Author create(AuthorRequest request) {
        Author author = new Author();
        this.prepare(author,request);
        authorRepository.save(author);
        return author;
    }

    public Author update(Long id, AuthorRequest request){
        Author author = authorRepository.getById(id);
        this.prepare(author,request);
        return author;
    }

    public void delete(Long id){
        authorRepository.deleteById(id);
    }

    private Author prepare(Author author,AuthorRequest request) {
        author.setName(request.getName());
        author.setBirthDate(LocalDate.parse(request.getBirthDate()));
        Set<Book> books = new HashSet<>(bookRepository.findAllById(request.getBooks()));
        author.setBooks(books);
        return author;
    }
}
