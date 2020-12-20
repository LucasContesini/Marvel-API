package com.contesini.marvel.controller.dto.comic;

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
public class ComicDTO implements Serializable {
    private int id;
    private int digitalId;
    private String title;
    private Double issueNumber;
    private String variantDescription;
    private String description;
    private Date modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private int pageCount;
    private List<TextObjectDTO> textObjects;
    private String resourceURI;
    private List<UrlDTO> urls;
    private ItemDetailsDTO series;
    private List<ComicDateDTO> dates;
    private List<ComicPriceDTO> prices;
    private ThumbnailDTO thumbnail;
    private List<ComicImageDTO> images;
    private ItemDetailsDTO creators;
    private ItemDetailsDTO characters;
    private ItemDetailsDTO stories;
    private ItemDetailsDTO events;
}
