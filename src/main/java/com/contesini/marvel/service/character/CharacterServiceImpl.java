package com.contesini.marvel.service.character;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.comic.*;
import com.contesini.marvel.controller.dto.common.ItemDTO;
import com.contesini.marvel.controller.dto.common.ItemDetailsDTO;
import com.contesini.marvel.controller.dto.common.ThumbnailDTO;
import com.contesini.marvel.controller.dto.common.UrlDTO;
import com.contesini.marvel.controller.dto.container.*;
import com.contesini.marvel.controller.dto.event.EventDTO;
import com.contesini.marvel.controller.dto.series.SeriesDTO;
import com.contesini.marvel.controller.dto.story.StoryDTO;
import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.*;
import com.contesini.marvel.model.comic.*;
import com.contesini.marvel.model.common.Thumbnail;
import com.contesini.marvel.model.common.Url;
import com.contesini.marvel.repository.*;
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
    private SeriesRepository seriesRepository;
    @Autowired
    private ComicRepository comicRepository;
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

    @Override
    public SeriesDataContainer findSeriesByCharacterId(Specification<Series> spec, int offset, int limit, String orderBy) {
        SearchServiceFactory<Story> searchServiceFactory = new SearchServiceFactory<>();

        Pageable pageable = searchServiceFactory.buildPageRequest(offset, limit, orderBy.toLowerCase());
        Page<Series> page = seriesRepository.findAll(spec, pageable);

        List<Series> series = page.getContent();
        List<SeriesDTO> seriesDTOS = series.stream().map(s -> convert(s)).collect(Collectors.toList());

        return DataBuildUtil.buildSeriesContainer(seriesDTOS, seriesDTOS.size(), limit, offset, (int) page.getTotalElements());
    }

    @Override
    public ComicDataContainer findComicsByCharacterId(Specification<Comic> spec, int offset, int limit, String orderBy) {
        SearchServiceFactory<Story> searchServiceFactory = new SearchServiceFactory<>();

        Pageable pageable = searchServiceFactory.buildPageRequest(offset, limit, orderBy.toLowerCase());
        Page<Comic> page = comicRepository.findAll(spec, pageable);

        List<Comic> comics = page.getContent();
        List<ComicDTO> comicDTOS = comics.stream().map(c -> convert(c)).collect(Collectors.toList());

        return DataBuildUtil.buildComicContainer(comicDTOS, comicDTOS.size(), limit, offset, (int) page.getTotalElements());
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

    private SeriesDTO convert(Series series) {
        try {
            SeriesDTO seriesDTO = new SeriesDTO();
            seriesDTO.setId(series.getId());
            seriesDTO.setTitle(series.getTitle());
            seriesDTO.setDescription(series.getDescription());
            seriesDTO.setEndYear(series.getEndYear());
            seriesDTO.setStartYear(series.getStartYear());
            seriesDTO.setModified(series.getModified());
            seriesDTO.setRating(series.getRating());
            seriesDTO.setResourceURI(series.getResourceURI());

            seriesDTO.setThumbnail(convertThumbnail(series.getThumbnail()));
            seriesDTO.setUrls(convertUrls(series.getUrls()));

            Set<Character> characters = series.getCharacters();

            List<Set<Comic>> listComic = characters.stream().map(c -> c.getComics()).collect(Collectors.toList());
            if (listComic.size() > 0) {
                seriesDTO.setComics(convertComics(listComic.get(0)));
            }

            List<Set<Event>> listEvents = characters.stream().map(e -> e.getEvents()).collect(Collectors.toList());
            if (listEvents.size() > 0) {
                seriesDTO.setEvents(convertEvents(listEvents.get(0)));
            }

            List<Set<Story>> listStories = characters.stream().map(c -> c.getStories()).collect(Collectors.toList());
            if (listStories.size() > 0) {
                seriesDTO.setStories(convertStories(listStories.get(0)));
            }
            seriesDTO.setCharacters(convertCharacters(characters));

            List<Set<Creator>> listCreator = listComic.get(0).stream().map(c -> c.getCreators()).collect(Collectors.toList());
            if (listCreator.size() > 0) {
                seriesDTO.setCreators(convertCreators(listCreator.get(0)));
            }

            seriesDTO.setPrevious(convertSeriesToItem(series.getPrevious()));
            seriesDTO.setNext(convertSeriesToItem(series.getNext()));

            return seriesDTO;
        } catch (Exception e) {
            return null;
        }
    }

    private ComicDTO convert(Comic comic) {
        ComicDTO comicDTO = new ComicDTO();
        comicDTO.setId(comic.getId());
        comicDTO.setDigitalId(comic.getDigitalId());
        comicDTO.setTitle(comic.getTitle());
        comicDTO.setDescription(comic.getDescription());
        comicDTO.setVariantDescription(comic.getVariantDescription());
        comicDTO.setDiamondCode(comic.getDiamondCode());
        comicDTO.setEan(comic.getEan());
        comicDTO.setFormat(comic.getFormat());
        comicDTO.setIsbn(comic.getIsbn());
        comicDTO.setIssn(comic.getIssn());
        comicDTO.setUpc(comic.getUpc());
        comicDTO.setIssueNumber(comic.getIssueNumber());
        comicDTO.setModified(comic.getModified());
        comicDTO.setPageCount(comic.getPageCount());
        comicDTO.setResourceURI(comic.getResourceURI());

        comicDTO.setThumbnail(convertThumbnail(comic.getThumbnail()));
        comicDTO.setUrls(convertUrls(comic.getUrls()));

        Set<Character> characters = comic.getCharacters();

        List<Set<Series>> listSeries = characters.stream().map(c -> c.getSeries()).collect(Collectors.toList());
        if (listSeries.size() > 0) {
            comicDTO.setSeries(convertSeries(listSeries.get(0)));
        }

        List<Set<Event>> listEvents = characters.stream().map(e -> e.getEvents()).collect(Collectors.toList());
        if (listEvents.size() > 0) {
            comicDTO.setEvents(convertEvents(listEvents.get(0)));
        }

        List<Set<Story>> listStories = characters.stream().map(c -> c.getStories()).collect(Collectors.toList());
        if (listStories.size() > 0) {
            comicDTO.setStories(convertStories(listStories.get(0)));
        }
        comicDTO.setCharacters(convertCharacters(characters));

        Set<Creator> listCreator = comic.getCreators();
        if (listCreator.size() > 0) {
            comicDTO.setCreators(convertCreators(listCreator));
        }

        comicDTO.setTextObjects(converTextObjects(comic.getTextObjects()));
        comicDTO.setDates(convertDates(comic.getComicDates()));
        comicDTO.setPrices(convertPrices(comic.getComicPrices()));
        comicDTO.setImages(convertImages(comic.getComicImages()));

        return comicDTO;
    }

    private ItemDetailsDTO convertCharacters(Set<Character> characters) {
        if (characters == null) {
            return null;
        }
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

    private ItemDetailsDTO convertSeries(Set<Series> seriesSet) {
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

    private ItemDetailsDTO convertStories(Set<Story> stories) {
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

    private ItemDetailsDTO convertEvents(Set<Event> events) {
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

    private ItemDetailsDTO convertCreators(Set<Creator> creators) {
        if (creators == null) {
            return null;
        }
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

    private ItemDTO convertEventToItem(Event event) {
        if (event == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(event.getTitle());
        itemDTO.setResourceURI(event.getResourceURI());

        return itemDTO;
    }

    private ItemDTO convertSeriesToItem(Series series) {
        if (series == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(series.getTitle());
        itemDTO.setResourceURI(series.getResourceURI());

        return itemDTO;
    }

    private List<TextObjectDTO> converTextObjects(List<TextObject> objects) {
        if (objects == null) {
            return null;
        }
        List<TextObjectDTO> objectDTOS = new ArrayList<>();

        objects.forEach(object -> {
            TextObjectDTO objectDTO = new TextObjectDTO();
            objectDTO.setLanguage(object.getLanguage());
            objectDTO.setText(object.getText());
            objectDTO.setType(object.getType());

            objectDTOS.add(objectDTO);
        });

        return objectDTOS;
    }

    private List<ComicDateDTO> convertDates(List<ComicDate> dates) {
        if (dates == null) {
            return null;
        }
        List<ComicDateDTO> dateDTOS = new ArrayList<>();

        dates.forEach(date -> {
            ComicDateDTO comicDateDTO = new ComicDateDTO();
            comicDateDTO.setDate(date.getDate());
            comicDateDTO.setType(date.getType());

            dateDTOS.add(comicDateDTO);
        });

        return dateDTOS;
    }

    private List<ComicPriceDTO> convertPrices(List<ComicPrice> prices) {
        if (prices == null) {
            return null;
        }
        List<ComicPriceDTO> priceDTOS = new ArrayList<>();

        prices.forEach(price -> {
            ComicPriceDTO comicPriceDTO = new ComicPriceDTO();
            comicPriceDTO.setPrice(price.getPrice());
            comicPriceDTO.setType(price.getType());

            priceDTOS.add(comicPriceDTO);
        });

        return priceDTOS;
    }

    private List<ComicImageDTO> convertImages(List<ComicImage> images) {
        if (images == null) {
            return null;
        }
        List<ComicImageDTO> comicImageDTOS = new ArrayList<>();

        images.forEach(image -> {
            ComicImageDTO comicImageDTO = new ComicImageDTO();
            comicImageDTO.setExtension(image.getExtension());
            comicImageDTO.setPath(image.getPath());

            comicImageDTOS.add(comicImageDTO);
        });

        return comicImageDTOS;
    }
}
