package bahlwan.library.homework.controllers;


import bahlwan.library.homework.dtos.BookRequest;
import bahlwan.library.homework.models.Book;
import bahlwan.library.homework.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long bookId){
        return bookService.getBook(bookId);
    }

    @GetMapping
    public List<Book> getAllBooks(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return bookService.getAllBooks(pageable);
    }

    @PostMapping
    public Book create(@RequestBody BookRequest request){
        return bookService.create(request);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id,@RequestBody BookRequest request){
         return bookService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }
}
