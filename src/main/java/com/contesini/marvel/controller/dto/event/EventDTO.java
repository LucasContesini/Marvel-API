package com.contesini.marvel.controller.dto.event;

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
public class EventDTO implements Serializable {
    private int id;
    private String title;
    private String description;
    private String type;
    private Date modified;
    private ThumbnailDTO thumbnail;
    private String resourceURI;
    private ItemDetailsDTO comics;
    private ItemDetailsDTO series;
    private ItemDetailsDTO stories;
    private ItemDetailsDTO characters;
    private ItemDetailsDTO creators;
    private List<UrlDTO> urls;
}
