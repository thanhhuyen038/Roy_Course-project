package com.example.apiroy.Repository;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    @Query("SELECT t FROM Book t JOIN t.listGenre tl ON tl.id = ?1")
    List<Book> getBookByGenre(Long id);
}
