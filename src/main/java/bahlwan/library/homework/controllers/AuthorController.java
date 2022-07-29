package bahlwan.library.homework.controllers;

import bahlwan.library.homework.dtos.AuthorRequest;
import bahlwan.library.homework.dtos.AuthorResponse;
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
    AuthorService authorService;

    @GetMapping("/{id}")
    public AuthorResponse getAuthor(@PathVariable String authorId){
        return authorService.getAuthor(authorId);
    }

    @GetMapping
    public List<AuthorResponse> getAllAuthors(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return authorService.getAllAuthors(pageable);
    }

    @PostMapping
    public AuthorResponse create(@RequestBody AuthorRequest request){
        return authorService.create(request);
    }

    @PutMapping("/{id}")
    public AuthorResponse update(@PathVariable String id,@RequestBody AuthorRequest request){
        return authorService.update(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        authorService.delete(id);
    }
}