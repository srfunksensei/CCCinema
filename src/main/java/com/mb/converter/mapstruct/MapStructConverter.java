package com.mb.converter.mapstruct;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MapStructConverter<S, T> {

    T toDto(final S source);

    List<T> toDto(final List<S> source);
}
