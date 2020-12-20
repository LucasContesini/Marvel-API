package com.contesini.marvel.mock;

import com.contesini.marvel.model.character.Character;

import java.util.Date;
import java.util.Optional;

public class CharacterMock {

    public static final int EXISTENT_CHARACTER_ID = 1;
    public static final int NO_EXISTENT_CHARACTER_ID = 1000;

    public static Optional<Character> createOptionalCharacterMock() {
        Character character = new Character();
        character.setId(1);
        character.setName("name");
        character.setDescription("description");
        character.setModified(new Date());
        character.setResourceURI("resourceURI");

        return Optional.of(character);
    }

    public static Optional<Character> createEmptyOptionalCharacterMock() {
        return Optional.empty();
    }
}
