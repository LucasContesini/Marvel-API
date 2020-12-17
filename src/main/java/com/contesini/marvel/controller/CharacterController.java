package com.contesini.marvel.controller;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.character.CharacterDataContainer;
import com.contesini.marvel.controller.dto.character.CharacterDataWrapper;
import com.contesini.marvel.service.character.CharacterService;
import com.contesini.marvel.util.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(path = "/v1/public/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDataWrapper> findById(@PathVariable int characterId) {
        CharacterDTO characterDTO = characterService.findById(characterId);
        if (characterDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WrapperUtil.constructWrapper(new CharacterDataContainer(), HttpStatus.NOT_FOUND, "We couldn't find that character"));
        }
        return ResponseEntity.ok(WrapperUtil.constructWrapper(new CharacterDataContainer(0,0,0,0, Collections.singletonList(characterDTO)), HttpStatus.OK, null));
    }
}
