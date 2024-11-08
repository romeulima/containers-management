package com.romeudev.docker_manager.controllers;

import com.github.dockerjava.api.model.Container;
import com.romeudev.docker_manager.dto.CreateContainerRequestDTO;
import com.romeudev.docker_manager.dto.CreateContainerResponseDTO;
import com.romeudev.docker_manager.services.DockerContainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class DockerContainerController {

    private final DockerContainerService containerService;

    public DockerContainerController(DockerContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping
    public ResponseEntity<CreateContainerResponseDTO> createContainer(@RequestBody CreateContainerRequestDTO containerDTO) {
        String containerId = this.containerService.createContainer(containerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateContainerResponseDTO(containerId));
    }

    @PutMapping("/{id}/start")
    public ResponseEntity startContainer(@PathVariable(name = "id") String containerId) {
        this.containerService.startContainer(containerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/stop")
    public ResponseEntity stopContainer(@PathVariable(name = "id") String containerId) {
        this.containerService.stopContainer(containerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContainer(@PathVariable(name = "id") String containerId) {
        this.containerService.deleteContainer(containerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<Container>> listContainers(@RequestParam(required = false, defaultValue = "true") boolean showAll) {
        List<Container> containers = this.containerService.listContainers(showAll);
        return ResponseEntity.ok(containers);
    }
}
