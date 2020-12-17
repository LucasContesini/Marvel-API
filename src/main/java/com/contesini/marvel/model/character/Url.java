package com.contesini.marvel.model.character;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "url")
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    private Character character;
}
