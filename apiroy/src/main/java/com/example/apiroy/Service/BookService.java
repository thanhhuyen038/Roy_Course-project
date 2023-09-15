package com.example.apiroy.Service;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Chapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BookService {

    List<Book> getAllBook();

    Book getBookByID(Long id) throws  Exception;

    List<Chapter> getAllChaptersByBook(Long id);

    Book createBook(Book book);

    Book updateBook(Long id, Book bookDetails) throws Exception;

    Map<String, Boolean> deleteBook(Long id) throws Exception;

    Book postBook(Book book, Long userId);
    Book postCoverImg(MultipartFile file, Long id) throws Exception;

}
