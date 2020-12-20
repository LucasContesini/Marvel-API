package com.contesini.marvel.mock;

import com.contesini.marvel.repository.CharacterRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Rollback
public abstract class BaseMockConfigTest {
    @MockBean
    private CharacterRepository characterRepository;
}
