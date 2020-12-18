package com.contesini.marvel.controller;

import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.controller.dto.wrapper.DataWrapper;
import com.contesini.marvel.service.character.CharacterService;
import com.contesini.marvel.util.DataConstructUtil;
import com.contesini.marvel.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/public/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/{characterId}")
    public ResponseEntity<DataWrapper> findById(@PathVariable int characterId) {
        CharacterDataContainer container = characterService.findById(characterId);
        if (container.getResults().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataConstructUtil.constructWrapper(container, HttpStatus.NOT_FOUND, MessageUtil.CHARACTER_NOT_FOUND));
        }
        return ResponseEntity.ok(DataConstructUtil.constructWrapper(container, HttpStatus.OK, null));
    }
}
