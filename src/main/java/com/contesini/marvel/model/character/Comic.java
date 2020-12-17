package com.contesini.marvel.model.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "comic")
@Data
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "collection_URI")
    private String collectionURI;

    @OneToMany(mappedBy = "comic")
    private List<Item> items;

    @JsonIgnore
    @OneToOne
    private Character character;
}
