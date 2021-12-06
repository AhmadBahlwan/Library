package bahlwan.library.homework.controllers;

import bahlwan.library.homework.dtos.AuthorRequest;
import bahlwan.library.homework.models.Author;
import bahlwan.library.homework.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService AuthorService;

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Long AuthorId){
        return AuthorService.getAuthor(AuthorId);
    }

    @GetMapping
    public List<Author> getAllAuthors(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return AuthorService.getAllAuthors(pageable);
    }

    @PostMapping
    public Author create(@RequestBody AuthorRequest request){
        return AuthorService.create(request);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id,@RequestBody AuthorRequest request){
        return AuthorService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        AuthorService.delete(id);
    }
}