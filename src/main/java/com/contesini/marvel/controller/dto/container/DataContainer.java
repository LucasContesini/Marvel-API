package com.contesini.marvel.controller.dto.container;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public abstract class DataContainer implements Serializable {
    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
}
