package com.contesini.marvel.controller.dto.character;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class UrlDTO implements Serializable {
    private String type;
    private String url;
}
