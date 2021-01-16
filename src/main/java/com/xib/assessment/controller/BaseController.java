package com.xib.assessment.controller;

import org.springframework.http.ResponseEntity;

import java.net.URI;

public class BaseController {
    public <T> ResponseEntity<T> okResponse(T object) {
        return ResponseEntity.ok(object);
    }

    public <T> ResponseEntity<T> createdResponse(Long id) {
        return ResponseEntity.created(URI.create(String.format("team/%s", id))).build();
    }
}
