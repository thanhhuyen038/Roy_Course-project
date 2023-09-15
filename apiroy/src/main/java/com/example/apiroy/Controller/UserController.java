package com.example.apiroy.Controller;


import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.User;
import com.example.apiroy.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/nguoidung")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody User userDetails) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws Exception {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}/truyenyeuthich/")
    public List<Book> getListFavoriteBookByUser(@PathVariable(value = "id") Long id) {
        return userService.getListFavoriteBookByUser(id);
    }

    @GetMapping("/{id}/truyen/")
    public List<Book> getBookByUser(@PathVariable(value = "id") Long id) {
        return userService.getBookByUser(id);
    }



    @PostMapping("/{nguoidung_id}/yeuthich/{truyen_id}")
    public ResponseEntity<Void> addBookInFavorites(
            @PathVariable(value = "nguoidung_id") Long userId,
            @PathVariable(value = "truyen_id") Long bookId) {
        try {
            userService.addBookInFavorites(userId, bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{userId}/xoayeuthich/{bookId}")
    public ResponseEntity<Void> removeBookFromFavorites(@PathVariable(value = "bookId") Long bookId,
                                                                       @PathVariable(value = "userId") Long userId) {
        try {
            userService.removeBookFromFavorites(userId, bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable(value = "email") String email) {
        User user = userService.findUserByEmail(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestBody User user) throws Exception {
        try {
            System.out.println("[DEBUG] - " + user);
            return ResponseEntity.ok().body(userService.loginAccount(user));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
