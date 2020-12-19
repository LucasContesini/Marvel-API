package com.contesini.marvel.model.character;

import com.contesini.marvel.model.comic.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "creator")
@Data
public class Creator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "resource_URI")
    private String resourceURI;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "creator_comic",
            joinColumns = @JoinColumn(name = "creator_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics;
}
