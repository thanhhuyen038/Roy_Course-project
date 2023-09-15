package com.example.apiroy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "chuong")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "truyen_id")
    private Book book;

    @Column(name = "ten_chuong")
    private String chapterName;

    @Column(name = "noi_dung_chuong", length = 50000)
    private String content;

}
