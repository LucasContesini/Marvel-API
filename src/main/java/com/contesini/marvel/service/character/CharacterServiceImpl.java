package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.repository.CharacterRepository;
import com.contesini.marvel.util.DataConstructUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CharacterDataContainer findById(int id) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if (optionalCharacter.isEmpty()) {
            return DataConstructUtil.constructContainer(Collections.emptyList());
        }
        Character character = optionalCharacter.get();
        CharacterDTO characterDTO = convertToDTO(character);

        return DataConstructUtil.constructContainer(Collections.singletonList(characterDTO));
    }

    private CharacterDTO convertToDTO(Character character) {
        try {
            CharacterDTO characterDTO = objectMapper.convertValue(character, CharacterDTO.class);

            characterDTO.getComics().setAvailable(character.getComics().getItems().size());
            characterDTO.getComics().setReturned(characterDTO.getComics().getAvailable());

            characterDTO.getSeries().setAvailable(character.getSeries().getItems().size());
            characterDTO.getSeries().setReturned(characterDTO.getSeries().getAvailable());

            characterDTO.getStories().setAvailable(character.getStories().getItems().size());
            characterDTO.getStories().setReturned(characterDTO.getStories().getAvailable());

            characterDTO.getEvents().setAvailable(character.getEvents().getItems().size());
            characterDTO.getEvents().setReturned(characterDTO.getEvents().getAvailable());

            return characterDTO;
        } catch (Exception e) {
            return null;
        }
    }
}
