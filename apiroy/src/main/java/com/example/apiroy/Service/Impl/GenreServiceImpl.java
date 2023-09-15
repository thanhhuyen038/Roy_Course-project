package com.example.apiroy.Service.Impl;

import com.example.apiroy.Model.Book;
import com.example.apiroy.Model.Genre;
import com.example.apiroy.Repository.GenreRepository;
import com.example.apiroy.Service.GenreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long id) throws Exception {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));
        return genre;
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Long id, Genre genreDetails) throws Exception {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));

        genre.setId(genreDetails.getId());
        genre.setNameOfGenre(genreDetails.getNameOfGenre());

        return genreRepository.save(genre);
    }

    @Override
    public Map<String, Boolean> deleteGenre(Long id) throws Exception {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new Exception("Thể loại này không tồn tại: " + id));

        genreRepository.delete(genre);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public List<Book> getBookByGenre(Long id) {
        return genreRepository.getBookByGenre(id);
    }
}
