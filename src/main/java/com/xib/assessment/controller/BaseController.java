package com.xib.assessment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class BaseController {
    public <T> ResponseEntity<T> okResponse(T object) {
        return ResponseEntity.ok(object);
    }

    public <T> ResponseEntity<T> createdResponse(String path, Long id, T object) {
        return ResponseEntity.created(getURL(path, id)).body(object);
    }

    private URI getURL(String path, Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(id).toUri();
    }
}
