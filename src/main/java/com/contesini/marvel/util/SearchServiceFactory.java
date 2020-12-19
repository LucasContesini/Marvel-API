package com.contesini.marvel.util;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchServiceFactory<T> {

    private Class<T> clazz;

    public Pageable buildPageRequest(int offset, int limit, String orderBy) {
        if (orderBy.isBlank()) {
            return PageRequest.of(offset, limit);
        }

        List<Sort.Order> orders = new ArrayList<>();

        String[] split = orderBy.split(",");

        for (String s : split) {
            Sort.Direction direction = s.contains("-") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order order = new Sort.Order(direction, s.replace("-", ""));
            orders.add(order);
        }

        return PageRequest.of(offset, limit, Sort.by(orders));
    }
}
