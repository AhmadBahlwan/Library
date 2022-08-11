package bahlwan.library.homework.graphql.mutations;

import bahlwan.library.homework.models.Author;
import bahlwan.library.homework.repositories.AuthorRepository;
import bahlwan.library.homework.repositories.BookRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMutation implements GraphQLMutationResolver {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(String id, String name, String birthDate, List<String> booksId){
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setBirthDate(birthDate);
        author.setBooks(bookRepository.findAllById(booksId).stream().collect(Collectors.toSet()));
        return authorRepository.save(author);
    }
}
