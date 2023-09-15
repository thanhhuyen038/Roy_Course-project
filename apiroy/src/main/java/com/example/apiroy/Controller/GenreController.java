package com.example.apiroy.Controller;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Genre;
import com.example.apiroy.Service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/theloai")
@RequiredArgsConstructor
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping()
    public List<Genre> getAllGenre() {
        return genreService.getAllGenre();
    }

    @GetMapping("{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable(value = "id") Long id)
            throws Exception {

        return ResponseEntity.ok().body(genreService.getGenreById(id));
    }

    @PostMapping()
    public Genre createGenre(@Valid @RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @PutMapping("{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody Genre genreDetails) throws Exception {
        return ResponseEntity.ok(genreService.updateGenre(id, genreDetails));
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteGenre(@PathVariable(value = "id") Long id)
            throws Exception {
        return genreService.deleteGenre(id);
    }

    // id the loai
    @GetMapping("{id}/truyen/")
    public List<Book> getBookByGenre(@PathVariable(value = "id") Long id) {
        return genreService.getBookByGenre(id);
    }
}
