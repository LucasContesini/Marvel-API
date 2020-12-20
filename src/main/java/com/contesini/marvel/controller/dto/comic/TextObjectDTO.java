package com.contesini.marvel.controller.dto.comic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class TextObjectDTO implements Serializable {
    private String type;
    private String language;
    private String text;
}
