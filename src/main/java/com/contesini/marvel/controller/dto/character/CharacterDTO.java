package com.contesini.marvel.controller.dto.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CharacterDTO implements Serializable {
    private int id;
    private String name;
    private String description;
    private Date modified;
    private ThumbnailDTO thumbnail;
    private String resourceURI;
    private CharacterDetailsDTO comics;
    private CharacterDetailsDTO series;
    private CharacterDetailsDTO stories;
    private CharacterDetailsDTO events;
    private List<UrlDTO> urls;
}
