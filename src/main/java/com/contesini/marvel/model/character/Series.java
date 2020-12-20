package com.contesini.marvel.model.character;

import com.contesini.marvel.model.comic.Comic;
import com.contesini.marvel.model.common.Thumbnail;
import com.contesini.marvel.model.common.Url;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "series")
@Data
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Column(name = "resource_URI")
    private String resourceURI;
    private String rating;
    private Date modified;
    private int startYear;
    private int endYear;
    @OneToMany(mappedBy = "series")
    private List<Url> urls;
    @OneToOne(mappedBy = "series")
    private Thumbnail thumbnail;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "series")
    Set<Character> characters;

}
