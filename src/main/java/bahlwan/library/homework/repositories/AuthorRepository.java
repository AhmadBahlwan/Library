package bahlwan.library.homework.repositories;


import bahlwan.library.homework.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findAuthorById(Long id);
}
