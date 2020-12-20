package com.contesini.marvel.util;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.comic.ComicDTO;
import com.contesini.marvel.controller.dto.container.*;
import com.contesini.marvel.controller.dto.event.EventDTO;
import com.contesini.marvel.controller.dto.series.SeriesDTO;
import com.contesini.marvel.controller.dto.story.StoryDTO;
import com.contesini.marvel.controller.dto.wrapper.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public class DataBuildUtil {

    private final static String COPYRIGHT = "© 2020 MARVEL";
    private final static String ATTRIBUTION_TEXT = "Data provided by Marvel. © 2020 MARVEL";
    private final static String ATTRIBUTION_HTML = "<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2020 MARVEL</a>";

    public static DataWrapper buildWrapper(HttpStatus status, String message) {
        CharacterDataWrapper wrapper = new CharacterDataWrapper();

        DataBuildUtil.addWrapperStatus(wrapper, status, message);

        return wrapper;
    }

    public static DataWrapper buildWrapper(CharacterDataContainer container, HttpStatus status, String message) {
        CharacterDataWrapper wrapper = new CharacterDataWrapper();

        DataBuildUtil.addWrapperStatus(wrapper, status, message);
        DataBuildUtil.addWrapperInfo(wrapper);
        wrapper.setData(container);

        return wrapper;
    }

    public static DataWrapper buildWrapper(StoryDataContainer container, HttpStatus status, String message) {
        StoryDataWrapper wrapper = new StoryDataWrapper();

        DataBuildUtil.addWrapperStatus(wrapper, status, message);
        DataBuildUtil.addWrapperInfo(wrapper);
        wrapper.setData(container);

        return wrapper;
    }

    public static DataWrapper buildWrapper(EventDataContainer container, HttpStatus status, String message) {
        EventDataWrapper wrapper = new EventDataWrapper();

        DataBuildUtil.addWrapperStatus(wrapper, status, message);
        DataBuildUtil.addWrapperInfo(wrapper);
        wrapper.setData(container);

        return wrapper;
    }

    public static DataWrapper buildWrapper(SeriesDataContainer container, HttpStatus status, String message) {
        SeriesDataWrapper wrapper = new SeriesDataWrapper();

        DataBuildUtil.addWrapperStatus(wrapper, status, message);
        DataBuildUtil.addWrapperInfo(wrapper);
        wrapper.setData(container);

        return wrapper;
    }

    public static DataWrapper buildWrapper(ComicDataContainer container, HttpStatus status, String message) {
        ComicDataWrapper wrapper = new ComicDataWrapper();

        DataBuildUtil.addWrapperStatus(wrapper, status, message);
        DataBuildUtil.addWrapperInfo(wrapper);
        wrapper.setData(container);

        return wrapper;
    }

    public static CharacterDataContainer buildCharacterContainer(List<CharacterDTO> characterDTOS, int count, int limit, int offset, int total) {
        CharacterDataContainer container = new CharacterDataContainer();

        DataBuildUtil.addContainerInfo(container, count, limit, offset, total);
        container.setResults(characterDTOS);

        return container;
    }

    public static StoryDataContainer buildStoryContainer(List<StoryDTO> storyDTOS, int count, int limit, int offset, int total) {
        StoryDataContainer container = new StoryDataContainer();

        DataBuildUtil.addContainerInfo(container, count, limit, offset, total);
        container.setResults(storyDTOS);

        return container;
    }

    public static EventDataContainer buildEventContainer(List<EventDTO> eventDTOS, int count, int limit, int offset, int total) {
        EventDataContainer container = new EventDataContainer();

        DataBuildUtil.addContainerInfo(container, count, limit, offset, total);
        container.setResults(eventDTOS);

        return container;
    }

    public static SeriesDataContainer buildSeriesContainer(List<SeriesDTO> seriesDTOS, int count, int limit, int offset, int total) {
        SeriesDataContainer container = new SeriesDataContainer();

        DataBuildUtil.addContainerInfo(container, count, limit, offset, total);
        container.setResults(seriesDTOS);

        return container;
    }

    public static ComicDataContainer buildComicContainer(List<ComicDTO> comicDTOS, int count, int limit, int offset, int total) {
        ComicDataContainer container = new ComicDataContainer();

        DataBuildUtil.addContainerInfo(container, count, limit, offset, total);
        container.setResults(comicDTOS);

        return container;
    }

    private static void addWrapperStatus(DataWrapper wrapper, HttpStatus status, String message) {
        wrapper.setCode(status.value());
        wrapper.setStatus(message != null ? message : status.name());
    }

    private static void addWrapperInfo(DataWrapper wrapper) {
        wrapper.setCopyright(DataBuildUtil.COPYRIGHT);
        wrapper.setAttributionText(DataBuildUtil.ATTRIBUTION_TEXT);
        wrapper.setAttributionHTML(DataBuildUtil.ATTRIBUTION_HTML);
        wrapper.setStag(UUID.randomUUID());
    }

    private static void addContainerInfo(DataContainer container, int count, int limit, int offset, int total) {
        container.setCount(count);
        container.setLimit(limit);
        container.setOffset(offset);
        container.setTotal(total);
    }
}
