package com.example.apiroy.Service;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUser();

    User getUserById(Long id) throws Exception;

    User createUser(User user);

    User updateUser(Long id, User userDetails) throws Exception;

    Map<String, Boolean> deleteUser(Long id) throws Exception;


    public List<Book> getBookByUser(Long id);

//    Book postBook(Book book, Long nguoiDungId);

    List<Book> getListFavoriteBookByUser(Long id);

    Book addBookInFavorites(Long userId,Long bookId) throws Exception;

    Book removeBookFromFavorites(Long userId,Long bookId) throws Exception;

    User findUserByEmail(String email);

    User loginAccount(User account) throws Exception;
}
