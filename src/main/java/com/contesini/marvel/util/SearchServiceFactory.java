package com.contesini.marvel.util;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class SearchServiceFactory<T> {

    private Class<T> clazz;

    public Pageable buildPageRequest(int offset, int limit, String orderBy) {
        if (orderBy.isBlank()) {
            return PageRequest.of(offset, limit);
        }
        Sort.Direction direction = orderBy.contains("-") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(offset, limit, Sort.by(direction, orderBy.replace("-", "")));
    }
}
