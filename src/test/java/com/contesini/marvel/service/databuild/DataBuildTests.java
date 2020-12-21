package com.contesini.marvel.service.databuild;

import com.contesini.marvel.controller.dto.character.CharacterDTO;
import com.contesini.marvel.controller.dto.container.CharacterDataContainer;
import com.contesini.marvel.controller.dto.wrapper.CharacterDataWrapper;
import com.contesini.marvel.controller.dto.wrapper.DataWrapper;
import com.contesini.marvel.mock.BaseServiceMockConfigTest;
import com.contesini.marvel.util.DataBuildUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DataBuildTests extends BaseServiceMockConfigTest {


    @Test
    public void shouldReturnBuildWrapperWithOnlyCodeAndStatus() {
        //Arrange\
        HttpStatus status = HttpStatus.OK;
        final int OK = 200;
        final String MESSAGE = "OK";
        //Act
        DataWrapper dataWrapper = DataBuildUtil.buildWrapper(status, null);
        //Assert
        assertEquals(OK, dataWrapper.getCode());
        assertEquals(MESSAGE, dataWrapper.getStatus());
    }

    @Test
    public void shouldReturnBuildCharacterWrapper() {
        HttpStatus status = HttpStatus.OK;
        final int SIZE = 1;
        CharacterDataContainer container = new CharacterDataContainer();
        container.setResults(Collections.singletonList(new CharacterDTO()));

        CharacterDataWrapper dataWrapper = (CharacterDataWrapper) DataBuildUtil.buildWrapper(container, status, null);

        assertNotNull(dataWrapper.getData());
        assertEquals(SIZE, dataWrapper.getData().getResults().size());
    }
}
