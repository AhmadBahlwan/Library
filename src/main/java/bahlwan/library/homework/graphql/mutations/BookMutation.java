package bahlwan.library.homework.graphql.mutations;

import bahlwan.library.homework.models.Book;
import bahlwan.library.homework.repositories.AuthorRepository;
import bahlwan.library.homework.repositories.BookRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMutation implements GraphQLMutationResolver {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book createBook(String title, String description, String publishDate, List<String>authorsId){
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setPublishDate(LocalDate.parse(publishDate));
        book.setAuthors(authorRepository.findAllById(authorsId).stream().collect(Collectors.toSet()));
        return bookRepository.save(book);
    }
}
