package bahlwan.library.homework.graphql.queries;


import bahlwan.library.homework.graphql.BookFilter;
import bahlwan.library.homework.graphql.FilterField;
import bahlwan.library.homework.models.Author;
import bahlwan.library.homework.models.Book;
import bahlwan.library.homework.repositories.BookRepository;
import bahlwan.library.homework.services.BookService;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;


@Component
public class BookResolver implements GraphQLQueryResolver {

    public static final String AUTHORS = "authors";

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;


    public Book book(String id) {
        return bookService.getBookById(id);
    }

    public Iterable<Book> allBooks(DataFetchingEnvironment environment) {
        DataFetchingFieldSelectionSet s = environment.getSelectionSet();
        if (s.contains(AUTHORS))
            return bookRepository.findAll(fetchAuthors());
        else
            return bookRepository.findAll();
    }

    public Book bookById(Long id, DataFetchingEnvironment environment) {
        Specification<Book> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
        if (selectionSet.contains(AUTHORS))
            spec = spec.and(fetchAuthors());
        return bookRepository.findOne(spec).orElse(null);
    }

    private Specification<Book> fetchAuthors() {
        return (root, query, builder) -> {
            Fetch<Book, Author> f = root.fetch(AUTHORS, JoinType.LEFT);
            Join<Book, Author> join = (Join<Book, Author>) f;
            return join.getOn();
        };
    }

    private Specification<Book> byId(Long id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }


    public Iterable<Book> booksWithFilter(BookFilter filter) {
        Specification<Book> spec = null;
        try{
            if (filter.getTitle() != null)
                spec = byTitle(filter.getTitle());
            if (filter.getDescription() != null)
                spec = (spec == null ? byDescription(filter.getDescription()) : spec.and(byDescription(filter.getDescription())));
            if (filter.getPublishDate() != null)
                spec = (spec == null ? byPublishDate(filter.getPublishDate()) :
                        spec.and(byPublishDate(filter.getPublishDate())));
            if (spec != null)
                return bookRepository.findAll(spec);
            else
                return bookRepository.findAll();
        }catch(NullPointerException e){
            return bookRepository.findAll();
        }

    }

    private Specification<Book> byTitle(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("title"));
    }

    private Specification<Book> byDescription(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("description"));
    }

    private Specification<Book> byPublishDate(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("publishDate"));
    }
}
