package com.contesini.marvel.controller.dto.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CharacterDataWrapper implements Serializable {
    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private CharacterDataContainer data;
}
