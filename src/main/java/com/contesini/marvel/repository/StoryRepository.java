package com.contesini.marvel.repository;

import com.contesini.marvel.model.character.Character;
import com.contesini.marvel.model.character.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer>, JpaSpecificationExecutor<Story> {
}
