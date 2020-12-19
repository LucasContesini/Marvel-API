package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.repository.CharacterRepository;
import com.contesini.marvel.util.DataConstructUtil;
import com.contesini.marvel.util.SearchServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CharacterDataContainer findWithFilter(Specification<Character> spec, int offset, int limit, String orderBy) {
        SearchServiceFactory<Character> searchServiceFactory = new SearchServiceFactory<>();

        Pageable pageable = searchServiceFactory.buildPageRequest(offset, limit, orderBy.toLowerCase());
        Page<Character> page = characterRepository.findAll(spec, pageable);

        List<Character> characterList = page.getContent();
        List<CharacterDTO> characterDTOS = characterList.stream().map(c -> convert(c)).collect(Collectors.toList());

        return DataConstructUtil.constructContainer(characterDTOS, characterDTOS.size(), limit, offset, (int) page.getTotalElements());
    }

    @Override
    public CharacterDataContainer findById(int id, int offset, int limit) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if (optionalCharacter.isEmpty()) {
            return DataConstructUtil.constructContainer(Collections.emptyList(), 0, limit, offset, 0);
        }

        Character character = optionalCharacter.get();
        List<CharacterDTO> characterList = Collections.singletonList(convert(character));

        return DataConstructUtil.constructContainer(characterList, characterList.size(), 0, 0, characterList.size());
    }

    private CharacterDTO convert(Character character) {
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
