package com.contesini.marvel.repository;

import com.contesini.marvel.model.comic.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Integer>, JpaSpecificationExecutor<Comic> {
}
