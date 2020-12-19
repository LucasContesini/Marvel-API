package com.contesini.marvel.controller;

import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.controller.dto.wrapper.DataWrapper;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.service.character.CharacterService;
import com.contesini.marvel.util.DataConstructUtil;
import com.contesini.marvel.util.MessageUtil;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/public/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<DataWrapper> findWithFilter(@And({
            @Spec(path = "name", params = "name", spec = EqualIgnoreCase.class),
            @Spec(path = "name", params = "nameStartsWith", spec = StartingWithIgnoreCase.class),
            @Spec(path = "modified", params = "modifiedSince", spec = Equal.class)
    }) Specification<Character> spec, @RequestParam(value = "limit", defaultValue = "20", required = false) int limit, @RequestParam(value = "offset", defaultValue = "0", required = false) int offset, @RequestParam(value = "orderBy", defaultValue = "", required = false) String orderBy) {
        CharacterDataContainer container = characterService.findWithFilter(spec, offset, limit, orderBy);
        return ResponseEntity.ok(DataConstructUtil.constructWrapper(container, HttpStatus.OK, null));
    }


    @GetMapping("/{characterId}")
    public ResponseEntity<DataWrapper> findById(@PathVariable int characterId, @RequestParam(value = "limit", defaultValue = "20", required = false) int limit, @RequestParam(value = "offset", defaultValue = "0", required = false) int offset) {
        CharacterDataContainer container = characterService.findById(characterId, offset, limit);
        if (container.getResults().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataConstructUtil.constructWrapper(container, HttpStatus.NOT_FOUND, MessageUtil.CHARACTER_NOT_FOUND));
        }
        return ResponseEntity.ok(DataConstructUtil.constructWrapper(container, HttpStatus.OK, null));
    }
}
