package com.example.apiroy.Service;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Genre;

import java.util.List;
import java.util.Map;

public interface GenreService {
    List<Genre> getAllGenre();
    Genre getGenreById(Long id) throws Exception;
    Genre createGenre(Genre genre);
    Genre updateGenre(Long id, Genre genreDetails) throws Exception;
    Map<String, Boolean> deleteGenre(Long id) throws Exception;
    List<Book> getBookByGenre(Long id);
}
