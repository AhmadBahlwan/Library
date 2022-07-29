package bahlwan.library.homework.graphql.converters;

import bahlwan.library.homework.dtos.BookResponse;
import bahlwan.library.homework.models.Book;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookToBookResponseConverter implements Converter<Book, BookResponse> {

    @Override
    public BookResponse convert(@NonNull Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setPublish_date(String.valueOf(book.getPublishDate()));
        List<String>authorIds = new ArrayList<>();
        book.getAuthors().forEach(author -> authorIds.add(author.getId()));
        bookResponse.setAuthors(authorIds);
        return bookResponse;
    }

    public List<BookResponse>convertAll(List<Book>books){
        List<BookResponse>response = new ArrayList<>();
        books.forEach(book -> response.add(convert(book)));
        return response;
    }
}