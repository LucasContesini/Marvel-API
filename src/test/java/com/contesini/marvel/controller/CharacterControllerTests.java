package com.contesini.marvel.controller;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.mock.BaseControllerMockConfigTest;
import com.contesini.marvel.mock.CharacterMock;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.service.character.CharacterService;
import com.contesini.marvel.util.DataBuildUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CharacterController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CharacterControllerTests extends BaseControllerMockConfigTest {

    public static final String FIND_CHARACTER_BY_ID_PATH = "/v1/public/characters/{characterId}";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CharacterService characterService;

    @Test
    public void shouldReturnSuccessWhenSendExistentCharacterId() throws Exception {
        Optional<Character> optionalCharacterMock = CharacterMock.createOptionalCharacterMock();
        CharacterDTO characterDTO = CharacterMock.convert(optionalCharacterMock.get());
        CharacterDataContainer container = DataBuildUtil.buildCharacterContainer(Collections.singletonList(characterDTO), 1, 20, 0, 1);

        doReturn(container).when(characterService).findById(CharacterMock.EXISTENT_CHARACTER_ID, 0, 20);

        mockMvc.perform(get(FIND_CHARACTER_BY_ID_PATH, CharacterMock.EXISTENT_CHARACTER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.code", is(200)));
    }

    @Test
    public void shouldReturnFailedWhenSendNoExistentCharacterId() throws Exception {
        CharacterDataContainer container = DataBuildUtil.buildCharacterContainer(Collections.emptyList(), 1, 20, 0, 1);

        doReturn(container).when(characterService).findById(CharacterMock.NO_EXISTENT_CHARACTER_ID, 0, 20);

        mockMvc.perform(get(FIND_CHARACTER_BY_ID_PATH, CharacterMock.NO_EXISTENT_CHARACTER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.code", is(404)));
    }
}
