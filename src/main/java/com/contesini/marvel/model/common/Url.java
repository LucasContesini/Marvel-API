package com.contesini.marvel.model.common;

import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.Event;
import com.contesini.marvel.model.character.Series;
import com.contesini.marvel.model.comic.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "url")
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "url")
    private String url;
    @JsonIgnore
    @ManyToOne
    private Character character;

    @JsonIgnore
    @ManyToOne
    private Comic comic;
    @JsonIgnore
    @ManyToOne
    private Event event;
    @JsonIgnore
    @ManyToOne
    private Series series;
}
