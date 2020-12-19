package com.contesini.marvel.model.character;

import com.contesini.marvel.model.comic.Comic;
import com.contesini.marvel.model.common.Thumbnail;
import com.contesini.marvel.model.common.Url;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "character")
@Getter
@Setter
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", length = 500)
    private String description;
    private Date modified;
    @Column(name = "resource_URI")
    private String resourceURI;

    @OneToOne(mappedBy = "character")
    private Thumbnail thumbnail;
    @OneToMany(mappedBy = "character")
    private List<Url> urls;

    @ManyToMany
    @JoinTable(name = "character_comic",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics;

    @ManyToMany
    @JoinTable(name = "character_series",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "series_id"))
    private Set<Series> series;

    @ManyToMany
    @JoinTable(name = "character_event",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events;

    @ManyToMany
    @JoinTable(name = "character_story",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "story_id"))
    private Set<Story> stories;
}
