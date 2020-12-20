package com.contesini.marvel.model.comic;

import com.contesini.marvel.model.character.*;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.common.Thumbnail;
import com.contesini.marvel.model.common.Url;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "comic")
@Getter
@Setter
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int digitalId;
    private String title;
    private Double issueNumber;
    private String variantDescription;
    private String description;
    private Date modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private int pageCount;
    @Column(name = "resource_URI")
    private String resourceURI;

    @OneToMany(mappedBy = "comic")
    private List<TextObject> textObjects;
    @OneToMany(mappedBy = "comic")
    private List<Url> urls;
    @OneToOne(mappedBy = "comic")
    private Thumbnail thumbnail;
    @OneToMany(mappedBy = "comic")
    private List<ComicDate> comicDates;
    @OneToMany(mappedBy = "comic")
    private List<ComicPrice> comicPrices;
    @OneToMany(mappedBy = "comic")
    private List<ComicImage> comicImages;

    @ManyToMany(mappedBy = "comics")
    Set<Creator> creators;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "comics")
    Set<Character> characters;
}
