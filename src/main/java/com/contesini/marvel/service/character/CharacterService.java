package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.container.*;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.Event;
import com.contesini.marvel.model.character.Series;
import com.contesini.marvel.model.character.Story;
import com.contesini.marvel.model.comic.Comic;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {

    CharacterDataContainer findWithFilter(Specification<Character> spec, int offset, int limit, String orderBy);

    CharacterDataContainer findById(int id, int offset, int limit);

    EventDataContainer findEventsByCharacterId(Specification<Event> spec, int offset, int limit, String orderBy);

    SeriesDataContainer findSeriesByCharacterId(Specification<Series> spec, int offset, int limit, String orderBy);

    StoryDataContainer findStoriesByCharacterId(Specification<Story> spec, int offset, int limit, String orderBy);

    ComicDataContainer findComicsByCharacterId(Specification<Comic> spec, int offset, int limit, String orderBy);
}
