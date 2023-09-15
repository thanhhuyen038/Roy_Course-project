package com.example.apiroy.Repository;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT c FROM Chapter c JOIN c.book b ON b.id = ?1")
    List<Chapter> getAllChaptersByBook(Long id);
}
