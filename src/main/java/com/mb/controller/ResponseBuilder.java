package com.mb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static <T> ResponseEntity<T> created(final T body) {
        return of(HttpStatus.CREATED, body);
    }

    public static <T> ResponseEntity<T> badRequest() {
        return of(HttpStatus.BAD_REQUEST).build();
    }

    public static <T> ResponseEntity<T> ok() {
        return of(HttpStatus.OK).build();
    }

    public static <T> ResponseEntity<T> ok(final T body) {
        return of(HttpStatus.OK, body);
    }

    public static <T> ResponseEntity<T> notFound() {
        return of(HttpStatus.NOT_FOUND).build();
    }

    public static <T> ResponseEntity<T> of(final HttpStatus status, final T body) {
        return of(status).body(body);
    }

    public static ResponseEntity.BodyBuilder of(HttpStatus status) {
        return ResponseEntity.status(status);
    }
}
