package bahlwan.library.homework.services;

import bahlwan.library.homework.graphql.converters.BookToBookResponseConverter;
import bahlwan.library.homework.dtos.BookRequest;
import bahlwan.library.homework.dtos.BookResponse;
import bahlwan.library.homework.models.Author;
import bahlwan.library.homework.models.Book;
import bahlwan.library.homework.repositories.AuthorRepository;
import bahlwan.library.homework.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    BookToBookResponseConverter converter;

    public BookResponse getBook(String id){
        return converter.convert(bookRepository.findBookById(id));
    }

    public List<BookResponse> getAllBooks(Pageable pageable){
        Page<Book> books = bookRepository.findAll(pageable);
        return converter.convertAll(books.getContent());
    }

    @Override
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findBookById(id);
    }

    public BookResponse create(BookRequest request){
        Book book = new Book();
        this.prepare(book,request);
        return converter.convert(bookRepository.save(book));
    }

    public BookResponse update(String id, BookRequest request){
        Book book = bookRepository.getById(id);
        return converter.convert(this.prepare(book,request));
    }

    public void delete(String id){
        try{
            bookRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){

        }
    }


    private Book prepare(Book book,BookRequest request){
        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setPublishDate(LocalDate.parse(request.getPublish_date()));
        Set<Author> authors = new HashSet<>(authorRepository.findAllById(request.getAuthors()));
        book.setAuthors(authors);
        return book;
    }
}
