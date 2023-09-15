package com.example.apiroy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "the_loai")
@Getter
@Setter
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "ten_the_loai", nullable = false)
    private String nameOfGenre;

    @JsonIgnore
    @ManyToMany(mappedBy = "listGenre")
    private List<Book> listBook;

    public Genre(String genre) {
        this.setNameOfGenre(genre);
    }
}
