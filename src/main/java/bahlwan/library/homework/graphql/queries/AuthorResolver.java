package bahlwan.library.homework.graphql.queries;

import bahlwan.library.homework.graphql.AuthorFilter;
import bahlwan.library.homework.graphql.FilterField;
import bahlwan.library.homework.graphql.PaginationFilter;
import bahlwan.library.homework.models.Author;

import bahlwan.library.homework.models.Book;
import bahlwan.library.homework.repositories.AuthorRepository;
import bahlwan.library.homework.repositories.BookRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Component
public class AuthorResolver implements GraphQLQueryResolver {

    public static final String BOOKS = "books";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;



    public Iterable<Author> allAuthors(DataFetchingEnvironment environment, PaginationFilter pagination) {
        DataFetchingFieldSelectionSet s = environment.getSelectionSet();
        if (s.contains(BOOKS))
            return authorRepository.findAll(fetchBooks(),PageRequest.of(pagination.getPage(),pagination.getSize()));
        else
            return authorRepository.findAll(PageRequest.of(pagination.getPage(), pagination.getSize()));
    }

    public Author author(Long id, DataFetchingEnvironment environment) {
        Specification<Author> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
        if (selectionSet.contains(BOOKS))
            spec = spec.and(fetchBooks());
        return authorRepository.findOne(spec).orElse(null);
    }

    private Specification<Author> fetchBooks() {
        return  (root, query, builder) -> {
            Fetch<Book, Author> f = root.fetch(BOOKS, JoinType.LEFT);
            Join<Book, Author> join = (Join<Book, Author>) f;
            return join.getOn();
        };
    }

    private Specification<Author> byId(Long id) {
        return  (root, query, builder) -> builder.equal(root.get("id"), id);
    }

    public Page<Author> authorsWithFilter(AuthorFilter filter, PaginationFilter pagination) {
        Specification<Author> spec = null;
        Pageable pageable = pagination == null ? PageRequest.of(0,100) : PageRequest.of(pagination.getPage(),pagination.getSize()) ;
        try{
            if (filter.getName() != null)
                spec = byName(filter.getName());
            if (filter.getBirthDate() != null)
                spec = (spec == null ? byBirthDate(filter.getBirthDate()) : spec.and(byBirthDate(filter.getBirthDate())));
            if (spec != null)
                return authorRepository.findAll(spec,pageable);
            else
                return authorRepository.findAll(pageable);
        }catch(NullPointerException e){
            return authorRepository.findAll(pageable);
        }
    }

    private Specification<Author> byName(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("name"));
    }

    private Specification<Author> byBirthDate(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("birthDate"));
    }

}
