package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.character.*;
import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.Event;
import com.contesini.marvel.model.character.Series;
import com.contesini.marvel.model.character.Story;
import com.contesini.marvel.model.comic.Comic;
import com.contesini.marvel.model.common.Thumbnail;
import com.contesini.marvel.model.common.Url;
import com.contesini.marvel.repository.CharacterRepository;
import com.contesini.marvel.util.DataConstructUtil;
import com.contesini.marvel.util.SearchServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final String BASE_COLLECTION_URI = "http://gateway.marvel.com/v1/public/";
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
            CharacterDTO characterDTO = new CharacterDTO();
            characterDTO.setId(character.getId());
            characterDTO.setDescription(character.getDescription());
            characterDTO.setModified(character.getModified());
            characterDTO.setName(character.getName());
            characterDTO.setResourceURI(character.getResourceURI());

            characterDTO.setComics(convertComics(character.getComics()));
            characterDTO.setSeries(convertSeries(character.getSeries()));
            characterDTO.setStories(convertStories(character.getStories()));
            characterDTO.setEvents(convertEvents(character.getEvents()));

            characterDTO.setThumbnail(convertThumbnail(character.getThumbnail()));
            characterDTO.setUrls(convertUrls(character.getUrls()));
            return characterDTO;
        } catch (Exception e) {
            return null;
        }
    }

    private CharacterDetailsDTO convertComics(Set<Comic> comics) {
        CharacterDetailsDTO detailsDTO = new CharacterDetailsDTO();
        detailsDTO.setReturned(comics.size());
        detailsDTO.setAvailable(detailsDTO.getReturned());
        List<ItemDTO> items = new ArrayList<>();
        comics.forEach(comic -> {
            if (detailsDTO.getCollectionURI() == null) {
                detailsDTO.setCollectionURI(comic.getResourceURI());
            }
            ItemDTO item = new ItemDTO();
            item.setName(comic.getTitle());
            item.setResourceURI(BASE_COLLECTION_URI + "/comics/" + comic.getId());
            items.add(item);
        });
        detailsDTO.setItems(items);
        return detailsDTO;
    }

    private CharacterDetailsDTO convertSeries(Set<Series> seriesSet) {
        CharacterDetailsDTO detailsDTO = new CharacterDetailsDTO();
        detailsDTO.setReturned(seriesSet.size());
        detailsDTO.setAvailable(detailsDTO.getReturned());
        List<ItemDTO> items = new ArrayList<>();
        seriesSet.forEach(series -> {
            if (detailsDTO.getCollectionURI() == null) {
                detailsDTO.setCollectionURI(series.getResourceURI());
            }
            ItemDTO item = new ItemDTO();
            item.setName(series.getTitle());
            item.setResourceURI(BASE_COLLECTION_URI + "/series/" + series.getId());
            items.add(item);
        });
        detailsDTO.setItems(items);
        return detailsDTO;
    }

    private CharacterDetailsDTO convertStories(Set<Story> stories) {
        CharacterDetailsDTO detailsDTO = new CharacterDetailsDTO();
        detailsDTO.setReturned(stories.size());
        detailsDTO.setAvailable(detailsDTO.getReturned());
        List<ItemDTO> items = new ArrayList<>();
        stories.forEach(story -> {
            if (detailsDTO.getCollectionURI() == null) {
                detailsDTO.setCollectionURI(story.getResourceURI());
            }
            ItemDTO item = new ItemDTO();
            item.setName(story.getTitle());
            item.setResourceURI(BASE_COLLECTION_URI + "/stories/" + story.getId());
            item.setType(story.getType());
            items.add(item);
        });
        detailsDTO.setItems(items);
        return detailsDTO;
    }

    private CharacterDetailsDTO convertEvents(Set<Event> events) {
        CharacterDetailsDTO detailsDTO = new CharacterDetailsDTO();
        detailsDTO.setReturned(events.size());
        detailsDTO.setAvailable(detailsDTO.getReturned());
        List<ItemDTO> items = new ArrayList<>();
        events.forEach(event -> {
            if (detailsDTO.getCollectionURI() == null) {
                detailsDTO.setCollectionURI(event.getResourceURI());
            }
            ItemDTO item = new ItemDTO();
            item.setName(event.getTitle());
            item.setResourceURI(BASE_COLLECTION_URI + "/events/" + event.getId());
            items.add(item);
        });
        detailsDTO.setItems(items);
        return detailsDTO;
    }

    private ThumbnailDTO convertThumbnail(Thumbnail thumbnail) {
        if(thumbnail == null) {
            return null;
        }
        ThumbnailDTO thumbnailDTO = new ThumbnailDTO();
        thumbnailDTO.setPath(thumbnail.getPath());
        thumbnailDTO.setExtension(thumbnail.getExtension());
        return thumbnailDTO;
    }

    private List<UrlDTO> convertUrls(List<Url> urls) {
        List<UrlDTO> urlDTOS = new ArrayList<>();

        urls.forEach(url -> {
            UrlDTO urlDTO = new UrlDTO();
            urlDTO.setUrl(url.getUrl());
            urlDTO.setType(url.getType());

            urlDTOS.add(urlDTO);
        });
        return urlDTOS;
    }
}
