package bahlwan.library.homework.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "name",length = 1000)
    private String name;

    @Column(name = "birth_date")
    private String birthDate;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors")
    Set<Book>books;

    public Author(){

    }

    public Author(String id, String name, String birthDate, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.books = books;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
