package com.example.apiroy.Controller;


import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Chapter;
import com.example.apiroy.Service.BookService;
import com.example.apiroy.Service.CoverImgService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/truyen")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CoverImgService coverImgService;

    @GetMapping()
    public ResponseEntity<?> getAllBook() {
        return ResponseEntity.ok().body(bookService.getAllBook());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(bookService.getBookByID(id));
    }

    @PostMapping("/{id}/dang_truyen")
    public ResponseEntity<?> postBook(@Valid @RequestBody Book book, @PathVariable(value = "id") Long userId) {
        Book bookMoi = bookService.postBook(book, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMoi);
    }

    @PutMapping("/{id}/cap-nhat-truyen")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody Book bookDetails) throws Exception {
        return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Long id)
            throws Exception {
        return bookService.deleteBook(id);
    }

    @GetMapping("/{id}/chuong/")
    public List<Chapter> getAllChaptersByBook(@PathVariable(value = "id") Long id) {
        return bookService.getAllChaptersByBook(id);
    }

    @PostMapping("/{id}/dang-anh")
    public ResponseEntity<?>  postCoverImg(@RequestParam("file") MultipartFile file, @PathVariable Long id){
        try {
            Book book = bookService.postCoverImg(file, id);
            return ResponseEntity.ok(book);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
