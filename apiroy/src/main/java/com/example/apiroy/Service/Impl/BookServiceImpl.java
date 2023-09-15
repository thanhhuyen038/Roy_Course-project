package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Chapter;
import com.example.apiroy.Model.User;
import com.example.apiroy.Repository.BookRepository;
import com.example.apiroy.Repository.GenreRepository;
import com.example.apiroy.Repository.UserRepository;
import com.example.apiroy.Service.BookService;
import com.example.apiroy.Service.CoverImgService;
import com.example.apiroy.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoverImgService coverImgService;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }


    @Override
    public Book getBookByID(Long id) throws  Exception{
        Book book = bookRepository.findById(id).orElseThrow(() -> new Exception("Truyện này không tồn tại: " + id));
        return book;
    }

    @Override
    public List<Chapter> getAllChaptersByBook(Long id) {
        return bookRepository.getAllChaptersByBook(id);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book postBook(Book book, Long userId) {
        System.out.println("[DEBUG] - START POST BOOK");
        User author = userRepository.findById(userId).get();
        book.setUser(author);
        Book createdBook = createBook(book);
        return createdBook;
    }

    @Override
    public Book postCoverImg(MultipartFile file, Long id) throws Exception {
        try {
            String url = coverImgService.uploadImage(file);
            Book book = bookRepository.findById(id).get();
            book.setCoverImg(url);
            bookRepository.save(book);
            return book;
        } catch (IOException e) {
            throw new Exception("Fail to upload image");
        }
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Truyện này không tồn tại: " + id));

        // So sánh và cập nhật tên truyện nếu có thay đổi
        if (!Objects.equals(book.getNameBook(), bookDetails.getNameBook())) {
            book.setNameBook(bookDetails.getNameBook());
        }
        // So sánh và cập nhật mô tả truyện nếu có thay đổi
        if (!Objects.equals(book.getDescribe(), bookDetails.getDescribe())) {
            book.setDescribe(bookDetails.getDescribe());
        }

//        // So sánh và cập nhật tên tác giả truyện nếu có thay đổi
//        if (!Objects.equals(book.getUser(), bookDetails.getUser())) {
//            book.setUser(bookDetails.getUser());
//        }
        // So sánh và cập nhật danh sách chapter nếu có thay đổi
        if (!Objects.equals(book.getListChapter(), bookDetails.getListChapter())) {
            book.setListChapter(bookDetails.getListChapter());
        }
        // So sánh và cập nhật thể loại nếu có thay đổi
        if (!Objects.equals(book.getListGenre(), bookDetails.getListGenre())) {
            book.setListGenre(bookDetails.getListGenre());
        }
        // So sánh và cập nhật ảnh bìa nếu có thay đổi
        if (!Objects.equals(book.getCoverImg(), bookDetails.getCoverImg())) {
            book.setCoverImg(bookDetails.getCoverImg());
        }

        return bookRepository.save(book);
    }

    @Override
    public Map<String, Boolean> deleteBook(Long id) throws Exception {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Truyện này không tồn tại: " + id));

        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
