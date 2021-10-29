package com.mb.converter.mapstruct;

import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface MapStructConverter<S, T> {

    T toDto(final S source);

    default List<T> toDto(final List<S> source) {
        if (source == null) {
            return null;
        }

        return source
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
