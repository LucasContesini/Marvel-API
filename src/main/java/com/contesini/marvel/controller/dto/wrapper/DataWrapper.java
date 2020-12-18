package com.contesini.marvel.controller.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.GUIDGenerator;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public abstract class DataWrapper implements Serializable {
    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private GUIDGenerator stag;
}
