package com.contesini.marvel.model.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "series")
@Data
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "collection_URI")
    private String collectionURI;

    @OneToMany(mappedBy = "series")
    private List<Item> items;
    @JsonIgnore
    @OneToOne
    private Character character;
}
