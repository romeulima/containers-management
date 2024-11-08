package com.romeudev.docker_manager.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockerImageService {

    private final DockerClient dockerClient;

    public DockerImageService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public List<Image> listAllImages() {
        return this.dockerClient.listImagesCmd().exec();
    }
}
