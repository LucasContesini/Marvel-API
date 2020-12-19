package com.contesini.marvel.model.character;

import com.contesini.marvel.model.comic.Comic;
import com.contesini.marvel.model.common.Thumbnail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "story")
@Data
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Column(name = "resource_URI")
    private String resourceURI;
    private String type;
    private Date modified;

    @OneToOne(mappedBy = "story")
    private Thumbnail thumbnail;

//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(name = "story_comic",
//            joinColumns = @JoinColumn(name = "story_id"),
//            inverseJoinColumns = @JoinColumn(name = "comic_id"))
//    private Set<Comic> comics;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "stories")
    Set<Character> characters;
}
