package com.contesini.marvel.model.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "resource_URI")
    private String resourceURI;
    @Column(name = "name")
    private String name;
    private String type;

    @JsonIgnore
    @ManyToOne
    private Comic comic;
    @JsonIgnore
    @ManyToOne
    private Series series;
    @JsonIgnore
    @ManyToOne
    private Story story;
    @JsonIgnore
    @ManyToOne
    private Event event;
}
