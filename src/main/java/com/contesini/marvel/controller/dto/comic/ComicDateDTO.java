package com.contesini.marvel.controller.dto.comic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ComicDateDTO implements Serializable {
    private String type;
    private Date date;
}
