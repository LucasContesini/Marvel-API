package com.contesini.marvel.controller.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public abstract class DataWrapper implements Serializable {
    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private UUID stag;
}
