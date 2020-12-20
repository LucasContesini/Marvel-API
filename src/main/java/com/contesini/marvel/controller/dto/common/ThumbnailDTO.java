package com.contesini.marvel.controller.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ThumbnailDTO implements Serializable {
    private String path;
    private String extension;
}
