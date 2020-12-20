package com.contesini.marvel.controller.dto.comic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ComicImageDTO implements Serializable {
    private String path;
    private String extension;
}
