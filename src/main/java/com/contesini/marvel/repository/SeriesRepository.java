package com.contesini.marvel.repository;

import com.contesini.marvel.model.character.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer>, JpaSpecificationExecutor<Series> {
}
