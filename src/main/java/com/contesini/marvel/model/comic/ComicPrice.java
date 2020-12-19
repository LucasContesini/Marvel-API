package com.contesini.marvel.model.comic;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comic_price")
@Data
public class ComicPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private Date date;

    @ManyToOne
    private Comic comic;
}
