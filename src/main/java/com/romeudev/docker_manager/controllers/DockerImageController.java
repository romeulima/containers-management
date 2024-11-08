package com.romeudev.docker_manager.controllers;

import com.github.dockerjava.api.model.Image;
import com.romeudev.docker_manager.services.DockerImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class DockerImageController {

    private final DockerImageService imageService;

    public DockerImageController(DockerImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<Image>> getImages() {
        List<Image> images = this.imageService.listAllImages();

        return ResponseEntity.ok(images);
    }
}
