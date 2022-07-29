package bahlwan.library.homework.graphql.converters;

import bahlwan.library.homework.dtos.AuthorResponse;
import bahlwan.library.homework.models.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorToAuthorResponseConverter implements Converter<Author,AuthorResponse> {

    @Override
    public AuthorResponse convert(@NonNull Author author) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setBirth_date(String.valueOf(author.getBirthDate()));
        List<String> bookIds = new ArrayList<>();
        author.getBooks().forEach(book -> bookIds.add(book.getId()));
        authorResponse.setBooks(bookIds);
        return authorResponse;
    }


    public List<AuthorResponse>convertAll(List<Author>authors){
        List<AuthorResponse>response = new ArrayList<>();
        authors.forEach(author -> response.add(convert(author)));
        return response;
    }
}
