package com.contesini.marvel.model.comic;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comic_image")
@Data
public class ComicImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String path;
    private String extension;

    @ManyToOne
    private Comic comic;
}
