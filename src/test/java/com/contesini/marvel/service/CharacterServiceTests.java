package com.contesini.marvel.service;

import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.mock.BaseServiceMockConfigTest;
import com.contesini.marvel.mock.CharacterMock;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.repository.CharacterRepository;
import com.contesini.marvel.service.character.CharacterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CharacterServiceTests extends BaseServiceMockConfigTest {

    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterRepository characterRepository;

    @Test
    public void shouldReturnCharacterContainerWithOneCharacterWhenFindExistentCharacterId() {
        Optional<Character> optionalCharacterMock = CharacterMock.createOptionalCharacterMock();

        doReturn(optionalCharacterMock).when(characterRepository).findById(CharacterMock.EXISTENT_CHARACTER_ID);

        CharacterDataContainer container = characterService.findById(CharacterMock.EXISTENT_CHARACTER_ID, 0, 10);

        assertNotNull(container.getResults());
        assertEquals(optionalCharacterMock.get().getId(), container.getResults().get(0).getId());
    }

    @Test
    public void shouldReturnCharacterContainerWithNoCharacterWhenFindNoExistentCharacterId() {
        final int SIZE = 0;

        Optional<Character> optionalCharacterMock = CharacterMock.createEmptyOptionalCharacterMock();

        doReturn(optionalCharacterMock).when(characterRepository).findById(CharacterMock.NO_EXISTENT_CHARACTER_ID);

        CharacterDataContainer container = characterService.findById(CharacterMock.NO_EXISTENT_CHARACTER_ID, 0, 10);

        assertEquals(SIZE, container.getResults().size());
    }
}
