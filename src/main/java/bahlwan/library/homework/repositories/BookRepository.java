package bahlwan.library.homework.repositories;

import bahlwan.library.homework.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book,String>, JpaSpecificationExecutor<Book> {

    Book findBookById(String id);
}
