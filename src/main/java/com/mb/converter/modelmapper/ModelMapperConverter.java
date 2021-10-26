package com.mb.converter.modelmapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ModelMapperConverter {

    protected ModelMapper modelMapper;

    public <S, T> T toDto(final S source, final Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public <S, T> List<T> toDto(final List<S> source, final Class<T> targetClass) {
        return source
                .stream()
                .map(element -> toDto(element, targetClass))
                .collect(Collectors.toList());
    }
}
