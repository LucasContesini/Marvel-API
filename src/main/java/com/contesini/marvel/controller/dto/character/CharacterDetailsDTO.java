package com.contesini.marvel.controller.dto.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CharacterDetailsDTO implements Serializable {
    private int available;
    private String collectionURI;
    private List<ItemDTO> items;
    private int returned;
}
