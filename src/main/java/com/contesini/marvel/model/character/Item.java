package com.contesini.marvel.model.character;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "resource_URI", nullable = false)
    private String resourceURI;
    @Column(name = "name", nullable = false)
    private String name;
    private String type;

    @ManyToOne
    private Comic comic;
    @ManyToOne
    private Series series;
    @ManyToOne
    private Story story;
    @ManyToOne
    private Event event;
}
