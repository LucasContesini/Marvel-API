package com.contesini.marvel.controller.dto.container;

import com.contesini.marvel.controller.dto.series.SeriesDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public class SeriesDataContainer extends DataContainer implements Serializable {
    private List<SeriesDTO> results;
}
