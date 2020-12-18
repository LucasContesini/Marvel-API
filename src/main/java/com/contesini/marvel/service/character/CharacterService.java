package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {
    CharacterDataContainer findById(int id);
}
