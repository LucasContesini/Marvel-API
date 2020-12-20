package com.contesini.marvel.controller.dto.series;

import com.contesini.marvel.controller.dto.common.ItemDTO;
import com.contesini.marvel.controller.dto.common.ItemDetailsDTO;
import com.contesini.marvel.controller.dto.common.ThumbnailDTO;
import com.contesini.marvel.controller.dto.common.UrlDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class SeriesDTO implements Serializable {
    private int id;
    private String title;
    private String description;
    private String resourceURI;
    private int startYear;
    private int endYear;
    private String rating;
    private Date modified;
    private ThumbnailDTO thumbnail;
    private ItemDetailsDTO comics;
    private ItemDetailsDTO events;
    private ItemDetailsDTO stories;
    private ItemDetailsDTO characters;
    private ItemDetailsDTO creators;
    private List<UrlDTO> urls;
    private ItemDTO previous;
    private ItemDTO next;
}
