package com.romeudev.docker_manager.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.romeudev.docker_manager.dto.CreateContainerRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockerContainerService {

    private final DockerClient dockerClient;

    public DockerContainerService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public String createContainer(CreateContainerRequestDTO dto) {
        CreateContainerCmd createContainer = this.dockerClient.createContainerCmd(dto.imageName());

        if (dto.containerName() != null) {
            createContainer
                    .withName(dto.containerName());
        }

        if (dto.entrancePort() != null && dto.exitPort() != null) {
            ExposedPort entrancePort = ExposedPort.tcp(Integer.parseInt(dto.entrancePort()));
            ExposedPort exitPort = ExposedPort.tcp(Integer.parseInt(dto.exitPort()));

            createContainer.withExposedPorts(entrancePort, exitPort);
        }

        CreateContainerResponse response = createContainer.exec();

        return response.getId();
    }

    public void startContainer(String containerId) {
        this.dockerClient.startContainerCmd(containerId).exec();
    }

    public void stopContainer(String containerId) {
        this.dockerClient.stopContainerCmd(containerId).exec();
    }

    public void deleteContainer(String containerId) {
        this.dockerClient.removeContainerCmd(containerId).exec();
    }

    public List<Container> listContainers(boolean showAll) {
        return this.dockerClient.listContainersCmd().withShowAll(showAll).exec();
    }
}
