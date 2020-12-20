package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.common.ItemDTO;
import com.contesini.marvel.controller.dto.common.ItemDetailsDTO;
import com.contesini.marvel.controller.dto.common.ThumbnailDTO;
import com.contesini.marvel.controller.dto.common.UrlDTO;
import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.controller.dto.container.EventDataContainer;
import com.contesini.marvel.controller.dto.container.StoryDataContainer;
import com.contesini.marvel.controller.dto.event.EventDTO;
import com.contesini.marvel.controller.dto.story.StoryDTO;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.*;
import com.contesini.marvel.model.comic.Comic;
import com.contesini.marvel.model.common.Thumbnail;
import com.contesini.marvel.model.common.Url;
import com.contesini.marvel.repository.CharacterRepository;
import com.contesini.marvel.repository.EventRepository;
import com.contesini.marvel.repository.StoryRepository;
import com.contesini.marvel.util.DataBuildUtil;
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
    private StoryRepository storyRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CharacterDataContainer findWithFilter(Specification<Character> spec, int offset, int limit, String orderBy) {
        SearchServiceFactory<Character> searchServiceFactory = new SearchServiceFactory<>();

        Pageable pageable = searchServiceFactory.buildPageRequest(offset, limit, orderBy.toLowerCase());
        Page<Character> page = characterRepository.findAll(spec, pageable);

        List<Character> characterList = page.getContent();
        List<CharacterDTO> characterDTOS = characterList.stream().map(c -> convert(c)).collect(Collectors.toList());

        return DataBuildUtil.buildCharacterContainer(characterDTOS, characterDTOS.size(), limit, offset, (int) page.getTotalElements());
    }

    @Override
    public CharacterDataContainer findById(int id, int offset, int limit) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);
        if (optionalCharacter.isEmpty()) {
            return DataBuildUtil.buildCharacterContainer(Collections.emptyList(), 0, limit, offset, 0);
        }

        Character character = optionalCharacter.get();
        List<CharacterDTO> characterList = Collections.singletonList(convert(character));

        return DataBuildUtil.buildCharacterContainer(characterList, characterList.size(), 0, 0, characterList.size());
    }

    @Override
    public StoryDataContainer findStoriesByCharacterId(Specification<Story> spec, int offset, int limit, String orderBy) {
        SearchServiceFactory<Story> searchServiceFactory = new SearchServiceFactory<>();

        Pageable pageable = searchServiceFactory.buildPageRequest(offset, limit, orderBy.toLowerCase());
        Page<Story> page = storyRepository.findAll(spec, pageable);

        List<Story> stories = page.getContent();
        List<StoryDTO> storiesDTOs = stories.stream().map(s -> convert(s)).collect(Collectors.toList());

        return DataBuildUtil.buildStoryContainer(storiesDTOs, storiesDTOs.size(), limit, offset, (int) page.getTotalElements());
    }

    @Override
    public EventDataContainer findEventsByCharacterId(Specification<Event> spec, int offset, int limit, String orderBy) {
        SearchServiceFactory<Story> searchServiceFactory = new SearchServiceFactory<>();

        Pageable pageable = searchServiceFactory.buildPageRequest(offset, limit, orderBy.toLowerCase());
        Page<Event> page = eventRepository.findAll(spec, pageable);

        List<Event> events = page.getContent();
        List<EventDTO> eventsDTOs = events.stream().map(e -> convert(e)).collect(Collectors.toList());
        return DataBuildUtil.buildEventContainer(eventsDTOs, eventsDTOs.size(), limit, offset, (int) page.getTotalElements());
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

    private StoryDTO convert(Story story) {
        try {
            StoryDTO storyDTO = new StoryDTO();
            storyDTO.setId(story.getId());
            storyDTO.setTitle(story.getTitle());
            storyDTO.setModified(story.getModified());
            storyDTO.setType(story.getType());
            storyDTO.setThumbnail(convertThumbnail(story.getThumbnail()));
            storyDTO.setResourceURI(story.getResourceURI());
            storyDTO.setDescription(story.getDescription());

            Set<Character> characters = story.getCharacters();

            List<Set<Comic>> listComic = characters.stream().map(c -> c.getComics()).collect(Collectors.toList());
            if (listComic.size() > 0) {
                storyDTO.setComics(convertComics(listComic.get(0)));
            }

            List<Set<Series>> listSeries = characters.stream().map(c -> c.getSeries()).collect(Collectors.toList());
            if (listSeries.size() > 0) {
                storyDTO.setSeries(convertSeries(listSeries.get(0)));
            }

            List<Set<Event>> listEvents = characters.stream().map(c -> c.getEvents()).collect(Collectors.toList());
            if (listEvents.size() > 0) {
                storyDTO.setEvents(convertEvents(listEvents.get(0)));
            }

            storyDTO.setCharacters(convertCharacters(characters));

            List<Set<Creator>> listCreator = listComic.get(0).stream().map(c -> c.getCreators()).collect(Collectors.toList());
            if (listCreator.size() > 0) {
                storyDTO.setCreators(convertCreators(listCreator.get(0)));
            }
            return storyDTO;
        } catch (Exception e) {
            return null;
        }
    }

    private EventDTO convert(Event event) {
        try {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(event.getId());
            eventDTO.setDescription(event.getDescription());
            eventDTO.setTitle(event.getTitle());
            eventDTO.setEnd(event.getEnd());
            eventDTO.setStart(event.getStart());
            eventDTO.setModified(event.getModified());
            eventDTO.setResourceURI(event.getResourceURI());

            eventDTO.setThumbnail(convertThumbnail(event.getThumbnail()));
            eventDTO.setUrls(convertUrls(event.getUrls()));

            Set<Character> characters = event.getCharacters();

            List<Set<Comic>> listComic = characters.stream().map(c -> c.getComics()).collect(Collectors.toList());
            if (listComic.size() > 0) {
                eventDTO.setComics(convertComics(listComic.get(0)));
            }

            List<Set<Series>> listSeries = characters.stream().map(c -> c.getSeries()).collect(Collectors.toList());
            if (listSeries.size() > 0) {
                eventDTO.setSeries(convertSeries(listSeries.get(0)));
            }

            List<Set<Story>> listStories = characters.stream().map(c -> c.getStories()).collect(Collectors.toList());
            if (listStories.size() > 0) {
                eventDTO.setStories(convertStories(listStories.get(0)));
            }
            eventDTO.setCharacters(convertCharacters(characters));

            List<Set<Creator>> listCreator = listComic.get(0).stream().map(c -> c.getCreators()).collect(Collectors.toList());
            if (listCreator.size() > 0) {
                eventDTO.setCreators(convertCreators(listCreator.get(0)));
            }

            eventDTO.setPrevious(convertEventToItem(event.getPrevious()));
            eventDTO.setNext(convertEventToItem(event.getNext()));

            return eventDTO;
        } catch (Exception e) {
            return null;
        }
    }

    private ItemDetailsDTO convertCharacters(Set<Character> characters) {
        ItemDetailsDTO detailsDTO = new ItemDetailsDTO();
        detailsDTO.setReturned(characters.size());
        detailsDTO.setAvailable(detailsDTO.getReturned());
        List<ItemDTO> items = new ArrayList<>();
        characters.forEach(character -> {
            if (detailsDTO.getCollectionURI() == null) {
                detailsDTO.setCollectionURI(character.getResourceURI());
            }
            ItemDTO item = new ItemDTO();
            item.setName(character.getName());
            item.setResourceURI(BASE_COLLECTION_URI + "/characters/" + character.getId());
            items.add(item);
        });
        detailsDTO.setItems(items);
        return detailsDTO;
    }

    private ItemDetailsDTO convertComics(Set<Comic> comics) {
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

    private ItemDetailsDTO convertSeries(Set<Series> seriesSet) {
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

    private ItemDetailsDTO convertStories(Set<Story> stories) {
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

    private ItemDetailsDTO convertEvents(Set<Event> events) {
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

    private ItemDetailsDTO convertCreators(Set<Creator> creators) {
        ItemDetailsDTO detailsDTO = new ItemDetailsDTO();
        detailsDTO.setReturned(creators.size());
        detailsDTO.setAvailable(detailsDTO.getReturned());
        List<ItemDTO> items = new ArrayList<>();
        creators.forEach(creator -> {
            if (detailsDTO.getCollectionURI() == null) {
                detailsDTO.setCollectionURI(creator.getResourceURI());
            }
            ItemDTO item = new ItemDTO();
            item.setName(creator.getName());
            item.setRole(creator.getRole());
            item.setResourceURI(BASE_COLLECTION_URI + "/creators/" + creator.getId());
            items.add(item);
        });
        detailsDTO.setItems(items);
        return detailsDTO;
    }

    private ThumbnailDTO convertThumbnail(Thumbnail thumbnail) {
        if (thumbnail == null) {
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

    private ItemDTO convertEventToItem(Event event) {
        if (event == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(event.getTitle());
        itemDTO.setResourceURI(event.getResourceURI());

        return itemDTO;
    }
}
