package bahlwan.library.homework.repositories;


import bahlwan.library.homework.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,String>, JpaSpecificationExecutor<Author>, PagingAndSortingRepository<Author,String> {

    Author findAuthorById(String id);
    Page<Author>findAll(Pageable peaPageable);
}
