package com.example.apiroy.Repository;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT t FROM Book t JOIN t.listUserPressingLove nd ON nd.id = ?1")
    List<Book> getListFavoriteBookByUser(Long id);

    @Query("SELECT t FROM Book t JOIN t.user tg ON tg.id = ?1")
    List<Book> getBookByUser(Long id);

    User findByEmail(String email);

    @Query("SELECT a FROM User a WHERE a.email = ?1 and a.password = ?2")
    Optional<User> loginAccount(String email, String password);
}
