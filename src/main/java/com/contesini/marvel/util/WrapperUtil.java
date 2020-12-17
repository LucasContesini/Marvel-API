package com.contesini.marvel.util;

import com.contesini.marvel.controller.dto.character.CharacterDataContainer;
import com.contesini.marvel.controller.dto.character.CharacterDataWrapper;
import org.springframework.http.HttpStatus;

public class WrapperUtil {

    private final static String COPYRIGHT = "© 2020 MARVEL";
    private final static String ATTRIBUTION_TEXT = "Data provided by Marvel. © 2020 MARVEL";
    private final static String ATTRIBUTION_HTML = "<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2020 MARVEL</a>";

    public static CharacterDataWrapper constructWrapper(CharacterDataContainer container, HttpStatus status, String message) {
        CharacterDataWrapper wrapper = new CharacterDataWrapper();
        wrapper.setCode(status.value());
        wrapper.setStatus(message != null ? message : status.name());
        if (status.is2xxSuccessful()) {
            wrapper.setData(container);
            wrapper.setCopyright(WrapperUtil.COPYRIGHT);
            wrapper.setAttributionText(WrapperUtil.ATTRIBUTION_TEXT);
            wrapper.setAttributionHTML(WrapperUtil.ATTRIBUTION_HTML);
        }
        return wrapper;
    }
}
