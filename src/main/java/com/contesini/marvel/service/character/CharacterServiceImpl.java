package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.repository.CharacterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CharacterDTO findById(int id) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if (optionalCharacter.isEmpty()) {
            return null;
        }
        Character character = optionalCharacter.get();
        return convertToDTO(character);
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
