package com.contesini.marvel.controller.dto.wrapper;

import com.contesini.marvel.controller.dto.container.EventDataContainer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public class EventDataWrapper extends DataWrapper implements Serializable {
    private EventDataContainer data;
}
