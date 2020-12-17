package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {
    CharacterDTO findById(int id);
}
