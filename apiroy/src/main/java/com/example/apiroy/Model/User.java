package com.example.apiroy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nguoi_dung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "ten_nguoi_dung", nullable = false)
    private String userName;
    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "mat_khau", nullable = false)
    private String password;

    @JsonIgnore //loi parse nen tam che lai
    @Column (name = "ngay_tao", nullable = false)
    private LocalDateTime createdAt;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "listUserPressingLove")
    private List<Book> listFavoriteBook;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Book> listBooks;

    public User(String username, String password, String email) {
        this.userName = username;
        this.password = password;
        this.email = email;
    }

}
