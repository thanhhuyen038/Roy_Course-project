package com.example.apiroy.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name = "truyen")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "ten_truyen", nullable = false)
    private String nameBook;

    @ManyToMany
    @JoinTable(
            name = "truyen_the_loai",
            joinColumns = @JoinColumn(name = "truyen_id"),
            inverseJoinColumns = @JoinColumn(name = "theloai_id")
    )
    private List<Genre> listGenre;

    @Column (name = "anh_bia", nullable = true)
    private String coverImg;

    @ManyToOne
    @JoinColumn(name = "nguoidung_id")
    private User user;

    @Column(name = "mo_ta_truyen", length = 5000)
    private String describe;

//    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "book")
    List<Chapter> listChapter;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "truyen_yeu_thich_nguoi_dung",
            joinColumns = @JoinColumn(name = "truyen_id"),
            inverseJoinColumns = @JoinColumn(name = "nguoidung_id")
    )
    private List<User> listUserPressingLove;



    public Book(String nameBook, User author, String describe, String coverImg, List<Genre> listGenre, List<Chapter> listChapter){
        this.setNameBook(nameBook);
        this.setUser(author);
        this.setDescribe(describe);
        this.setCoverImg(coverImg);
        this.setListGenre(listGenre);
        this.setListChapter(listChapter);
    }
}
