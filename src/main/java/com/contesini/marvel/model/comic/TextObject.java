package com.contesini.marvel.model.comic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "text_object")
@Data
public class TextObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String language;
    private String text;

    @JsonIgnore
    @ManyToOne
    private Comic comic;
}
