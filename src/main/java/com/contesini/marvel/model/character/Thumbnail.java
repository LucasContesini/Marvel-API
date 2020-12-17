package com.contesini.marvel.model.character;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "thumbnail")
@Data
public class Thumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "path", nullable = false)
    private String path;
    @Column(name = "extension", nullable = false)
    private String extension;
    @OneToOne
    private Character character;
}
