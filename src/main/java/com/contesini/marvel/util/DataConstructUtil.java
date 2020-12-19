package com.contesini.marvel.util;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.controller.dto.container.DataContainer;
import com.contesini.marvel.controller.dto.wrapper.CharacterDataWrapper;
import com.contesini.marvel.controller.dto.wrapper.DataWrapper;
import org.springframework.http.HttpStatus;

import java.util.List;

public class DataConstructUtil {

    private final static String COPYRIGHT = "© 2020 MARVEL";
    private final static String ATTRIBUTION_TEXT = "Data provided by Marvel. © 2020 MARVEL";
    private final static String ATTRIBUTION_HTML = "<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2020 MARVEL</a>";

    public static DataWrapper constructWrapper(CharacterDataContainer container, HttpStatus status, String message) {
        CharacterDataWrapper wrapper = new CharacterDataWrapper();

        DataConstructUtil.addWrapperStatus(wrapper, status, message);

        if (status.is2xxSuccessful()) {
            DataConstructUtil.addWrapperInfo(wrapper);
            wrapper.setData(container);
        }

        return wrapper;
    }

    public static CharacterDataContainer constructContainer(List<CharacterDTO> characterDTOS, int count, int limit, int offset, int total) {
        CharacterDataContainer container = new CharacterDataContainer();

        DataConstructUtil.addContainerInfo(container, count, limit, offset, total);
        container.setResults(characterDTOS);

        return container;
    }

    private static void addWrapperStatus(DataWrapper wrapper, HttpStatus status, String message) {
        wrapper.setCode(status.value());
        wrapper.setStatus(message != null ? message : status.name());
    }

    private static void addWrapperInfo(DataWrapper wrapper) {
        wrapper.setCopyright(DataConstructUtil.COPYRIGHT);
        wrapper.setAttributionText(DataConstructUtil.ATTRIBUTION_TEXT);
        wrapper.setAttributionHTML(DataConstructUtil.ATTRIBUTION_HTML);
    }

    private static void addContainerInfo(DataContainer container, int count, int limit, int offset, int total) {
        container.setCount(count);
        container.setLimit(limit);
        container.setOffset(offset);
        container.setTotal(total);
    }
}
