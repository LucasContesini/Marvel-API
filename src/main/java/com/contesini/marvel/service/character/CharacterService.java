package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.model.character.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {

    CharacterDataContainer findWithFilter(Specification<Character> spec, int offset, int limit, String orderBy);
    CharacterDataContainer findById(int id, int offset, int limit);
}
