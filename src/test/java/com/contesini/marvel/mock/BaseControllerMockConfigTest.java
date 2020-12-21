package com.contesini.marvel.mock;

import com.contesini.marvel.service.character.CharacterService;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class BaseControllerMockConfigTest {
    @MockBean
    private CharacterService characterService;
}
