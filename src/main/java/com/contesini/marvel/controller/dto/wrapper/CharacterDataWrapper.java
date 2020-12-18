package com.contesini.marvel.controller.dto.wrapper;

import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.GUIDGenerator;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public class CharacterDataWrapper extends DataWrapper implements Serializable {
    private CharacterDataContainer data;
}
