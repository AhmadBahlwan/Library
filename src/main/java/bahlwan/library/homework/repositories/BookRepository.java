package bahlwan.library.homework.repositories;

import bahlwan.library.homework.models.Author;
import bahlwan.library.homework.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book,String>, JpaSpecificationExecutor<Book>, PagingAndSortingRepository<Book,String> {

    Book findBookById(String id);
    Page<Book> findAll(Pageable pageable);
}
