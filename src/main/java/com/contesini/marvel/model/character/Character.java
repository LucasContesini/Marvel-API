package com.contesini.marvel.model.character;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "character")
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", length = 500)
    private String description;
    private LocalDateTime modified;
    @Column(name = "resource_URI", nullable = false)
    private String resourceURI;

    @OneToOne(mappedBy = "character")
    private Thumbnail thumbnail;
    @OneToOne(mappedBy = "character")
    private Comic comics;
    @OneToOne(mappedBy = "character")
    private Series serie;
    @OneToOne(mappedBy = "character")
    private Story story;
    @OneToOne(mappedBy = "character")
    private Event event;
    @OneToMany(mappedBy = "character")
    private List<Url> urls;
}
