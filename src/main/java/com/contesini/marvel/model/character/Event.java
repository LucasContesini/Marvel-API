package com.contesini.marvel.model.character;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "event")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "collection_URI", nullable = false)
    private String collectionURI;

    @OneToMany(mappedBy = "event")
    private List<Item> items;
    @OneToOne
    private Character character;
}
