package com.contesini.marvel.mock;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.common.ItemDTO;
import com.contesini.marvel.controller.dto.common.ItemDetailsDTO;
import com.contesini.marvel.controller.dto.common.ThumbnailDTO;
import com.contesini.marvel.controller.dto.common.UrlDTO;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.Event;
import com.contesini.marvel.model.character.Series;
import com.contesini.marvel.model.character.Story;
import com.contesini.marvel.model.comic.Comic;
import com.contesini.marvel.model.common.Thumbnail;
import com.contesini.marvel.model.common.Url;

import java.util.*;

public class CharacterMock {

    public static final int EXISTENT_CHARACTER_ID = 1;
    public static final int NO_EXISTENT_CHARACTER_ID = 1000;
    private static final String BASE_COLLECTION_URI = "http://gateway.marvel.com/v1/public/";

    public static Optional<Character> createOptionalCharacterMock() {
        Character character = new Character();
        character.setId(1);
        character.setName("name");
        character.setDescription("description");
        character.setModified(new Date());
        character.setResourceURI("resourceURI");

        return Optional.of(character);
    }

    public static Optional<Character> createEmptyOptionalCharacterMock() {
        return Optional.empty();
    }

    public static CharacterDTO convert(Character character) {
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

    private static ItemDetailsDTO convertComics(Set<Comic> comics) {
        if (comics == null) {
            return null;
        }
        ItemDetailsDTO detailsDTO = new ItemDetailsDTO();
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

    private static ItemDetailsDTO convertSeries(Set<Series> seriesSet) {
        if (seriesSet == null) {
            return null;
        }
        ItemDetailsDTO detailsDTO = new ItemDetailsDTO();
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

    private static ItemDetailsDTO convertStories(Set<Story> stories) {
        if (stories == null) {
            return null;
        }
        ItemDetailsDTO detailsDTO = new ItemDetailsDTO();
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

    private static ItemDetailsDTO convertEvents(Set<Event> events) {
        if (events == null) {
            return null;
        }
        ItemDetailsDTO detailsDTO = new ItemDetailsDTO();
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

    private static ThumbnailDTO convertThumbnail(Thumbnail thumbnail) {
        if (thumbnail == null) {
            return null;
        }
        ThumbnailDTO thumbnailDTO = new ThumbnailDTO();
        thumbnailDTO.setPath(thumbnail.getPath());
        thumbnailDTO.setExtension(thumbnail.getExtension());
        return thumbnailDTO;
    }

    private static List<UrlDTO> convertUrls(List<Url> urls) {
        if (urls == null) {
            return null;
        }
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
