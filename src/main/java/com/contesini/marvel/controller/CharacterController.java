package com.contesini.marvel.controller;

import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.controller.dto.container.EventDataContainer;
import com.contesini.marvel.controller.dto.container.StoryDataContainer;
import com.contesini.marvel.controller.dto.wrapper.DataWrapper;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.Event;
import com.contesini.marvel.model.character.Story;
import com.contesini.marvel.service.character.CharacterService;
import com.contesini.marvel.util.DataBuildUtil;
import com.contesini.marvel.util.MessageUtil;
import com.contesini.marvel.util.RequestParameterException;
import lombok.EqualsAndHashCode;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.StartingWithIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/public/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<DataWrapper> findWithFilter(
            @Join(path = "comics", alias = "c")
            @Join(path = "series", alias = "s")
            @Join(path = "stories", alias = "st")
            @Join(path = "events", alias = "e")
            @And({
                    @Spec(path = "name", params = "name", spec = EqualIgnoreCase.class),
                    @Spec(path = "name", params = "nameStartsWith", spec = StartingWithIgnoreCase.class),
                    @Spec(path = "modified", params = "modifiedSince", spec = Equal.class),
                    @Spec(path = "c.id", params = "comics", paramSeparator = ',', spec = In.class),
                    @Spec(path = "s.id", params = "series", paramSeparator = ',', spec = In.class),
                    @Spec(path = "st.id", params = "stories", paramSeparator = ',', spec = In.class),
                    @Spec(path = "e.id", params = "events", paramSeparator = ',', spec = In.class)
            }) Specification<Character> spec, @RequestParam(value = "limit", defaultValue = "20", required = false) int limit, @RequestParam(value = "offset", defaultValue = "0", required = false) int offset, @RequestParam(value = "orderBy", defaultValue = "", required = false) String orderBy) {
        try {
            checkLimit(limit);
            checkOffset(offset);

            CharacterDataContainer container = characterService.findWithFilter(spec, offset, limit, orderBy);
            return ResponseEntity.ok(DataBuildUtil.buildWrapper(container, HttpStatus.OK, null));
        } catch (RequestParameterException ex) {
            return ResponseEntity.status(ex.getStatus()).body(DataBuildUtil.buildWrapper(ex.getStatus(), ex.getMessage()));
        } catch (PropertyReferenceException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(DataBuildUtil.buildWrapper(HttpStatus.CONFLICT, ex.getPropertyName() + MessageUtil.ORDER_PARAMETER_INVALID));
        }
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<DataWrapper> findById(@PathVariable int characterId, @RequestParam(value = "limit", defaultValue = "20", required = false) int limit, @RequestParam(value = "offset", defaultValue = "0", required = false) int offset) {
        try {
            checkLimit(limit);
            checkOffset(offset);

            CharacterDataContainer container = characterService.findById(characterId, offset, limit);
            if (container.getResults().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataBuildUtil.buildWrapper(container, HttpStatus.NOT_FOUND, MessageUtil.CHARACTER_NOT_FOUND));
            }
            return ResponseEntity.ok(DataBuildUtil.buildWrapper(container, HttpStatus.OK, null));
        } catch (RequestParameterException ex) {
            return ResponseEntity.status(ex.getStatus()).body(DataBuildUtil.buildWrapper(ex.getStatus(), ex.getMessage()));
        }
    }

    @GetMapping("/{characterId}/stories")
    public ResponseEntity<DataWrapper> findStoriesByCharacterId(
            @Join(path = "characters", alias = "c")
            @And({
                    @Spec(path = "c.id", pathVars = "characterId", spec = Equal.class),
                    @Spec(path = "modified", params = "modifiedSince", spec = Equal.class)
            }) Specification<Story> spec, @RequestParam(value = "limit", defaultValue = "20", required = false) int limit, @RequestParam(value = "offset", defaultValue = "0", required = false) int offset, @RequestParam(value = "orderBy", defaultValue = "", required = false) String orderBy) {
        try {
            checkLimit(limit);
            checkOffset(offset);

            StoryDataContainer container = characterService.findStoriesByCharacterId(spec, offset, limit, orderBy);
            return ResponseEntity.ok(DataBuildUtil.buildWrapper(container, HttpStatus.OK, null));
        } catch (RequestParameterException ex) {
            return ResponseEntity.status(ex.getStatus()).body(DataBuildUtil.buildWrapper(ex.getStatus(), ex.getMessage()));
        } catch (PropertyReferenceException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(DataBuildUtil.buildWrapper(HttpStatus.CONFLICT, ex.getPropertyName() + MessageUtil.ORDER_PARAMETER_INVALID));
        }
    }

    @GetMapping("/{characterId}/events")
    public ResponseEntity<DataWrapper> findEventsByCharacterId(
            @Join(path = "characters", alias = "c")
            @And({
                    @Spec(path = "title", params = "name", spec = EqualIgnoreCase.class),
                    @Spec(path = "title", params = "nameStartsWith", spec = StartingWithIgnoreCase.class),
                    @Spec(path = "c.id", pathVars = "characterId", spec = Equal.class),
                    @Spec(path = "modified", params = "modifiedSince", spec = Equal.class)
            }) Specification<Event> spec, @RequestParam(value = "limit", defaultValue = "20", required = false) int limit, @RequestParam(value = "offset", defaultValue = "0", required = false) int offset, @RequestParam(value = "orderBy", defaultValue = "", required = false) String orderBy) {
        try {
            checkLimit(limit);
            checkOffset(offset);

            EventDataContainer container = characterService.findEventsByCharacterId(spec, offset, limit, orderBy);
            return ResponseEntity.ok(DataBuildUtil.buildWrapper(container, HttpStatus.OK, null));
        } catch (RequestParameterException ex) {
            return ResponseEntity.status(ex.getStatus()).body(DataBuildUtil.buildWrapper(ex.getStatus(), ex.getMessage()));
        } catch (PropertyReferenceException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(DataBuildUtil.buildWrapper(HttpStatus.CONFLICT, ex.getPropertyName() + MessageUtil.ORDER_PARAMETER_INVALID));
        }
    }

    private void checkLimit(int limit) throws RequestParameterException {
        if (limit <= 0) {
            throw new RequestParameterException(MessageUtil.LIMIT_GREATER_THAN_ZERO, HttpStatus.CONFLICT);
        } else if (limit > 100) {
            throw new RequestParameterException(MessageUtil.LIMIT_LESS_THAN_HUNDRED, HttpStatus.CONFLICT);
        }
    }

    private void checkOffset(int offset) throws RequestParameterException {
        if (offset < 0) {
            throw new RequestParameterException(MessageUtil.OFFSET_MUST_BE_POSITIVE, HttpStatus.CONFLICT);
        }
    }
}
