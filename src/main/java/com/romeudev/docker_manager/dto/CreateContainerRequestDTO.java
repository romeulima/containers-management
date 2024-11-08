package com.romeudev.docker_manager.dto;

import jakarta.validation.constraints.NotNull;

public record CreateContainerRequestDTO(
        @NotNull
        String imageName,

        String containerName,
        String exitPort,
        String entrancePort
) {
}
