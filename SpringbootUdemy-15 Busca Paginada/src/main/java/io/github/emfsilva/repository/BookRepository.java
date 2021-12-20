package io.github.emfsilva.repository;

import io.github.emfsilva.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    @Modifying
    @Query("UPDATE Book b SET b.enabled = false WHERE b.id =:id")
    void disableBooks(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Book b SET b.enabled = true WHERE b.id =:id")
    void enableBooks(@Param("id") Long id);

}