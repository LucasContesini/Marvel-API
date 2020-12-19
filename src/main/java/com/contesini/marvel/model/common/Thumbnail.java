package com.contesini.marvel.model.common;

import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.Event;
import com.contesini.marvel.model.character.Series;
import com.contesini.marvel.model.character.Story;
import com.contesini.marvel.model.comic.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "thumbnail")
@Data
public class Thumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "path")
    private String path;
    @Column(name = "extension")
    private String extension;
    @JsonIgnore
    @OneToOne
    private Character character;
    @JsonIgnore
    @OneToOne
    private Event event;
    @JsonIgnore
    @OneToOne
    private Story story;
    @JsonIgnore
    @OneToOne
    private Series series;
    @JsonIgnore
    @OneToOne
    private Comic comic;
}
