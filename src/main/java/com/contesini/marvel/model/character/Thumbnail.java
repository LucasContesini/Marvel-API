package com.contesini.marvel.model.character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "thumbnail")
@Data
public class Thumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "path")
    private String path;
    @Column(name = "extension")
    private String extension;
    @JsonIgnore
    @OneToOne
    private Character character;
}
