package com.contesini.marvel.controller.dto.wrapper;

import com.contesini.marvel.controller.dto.container.ComicDataContainer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public class ComicDataWrapper extends DataWrapper implements Serializable {
    private ComicDataContainer data;
}
