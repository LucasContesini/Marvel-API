package com.contesini.marvel.controller.dto.comic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ComicPriceDTO implements Serializable {
    private String type;
    private Float price;
}
