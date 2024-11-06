package com.bookstore.jpa.repositories;

import com.bookstore.jpa.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {

    BookModel findBookModelByTitle(String title);

    @Query(value = "SELECT * FROM tb_book WHERE publisher_id = :id", nativeQuery = true)
    List<BookModel> findBooksByPublisherId(@Param("id")UUID id);


    @Query("SELECT b.publisher.name, COUNT(b) FROM BookModel b GROUP BY b.publisher.name")
    List<Object[]> countBooksByPublisher();


    @Query("SELECT a.name, COUNT(b) FROM BookModel b JOIN b.authors a GROUP BY a.name")
    List<Object[]> countBooksByAuthor();

    @Query("SELECT SUM(b.price) FROM BookModel b")
    Double sumBookPrices();
}

