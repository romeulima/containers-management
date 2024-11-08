# Docker Containers Management

***

### Esse projeto foi feito para testar a comunicação e o uso do Client do Docker.

Nessa API você será capaz de de gerenciar seus containers docker. Desde a criação de um novo container, até a remoção do mesmo.

***

    Endpoints:
        1. /api/containers:
            POST: Cria um novo container - request body: imageName
            DELETE: /id - Deleta um container
            GET: Lista todos os container - requestParam: showAll - "true" ou "false" 
            PUT: /id/start - Inicia um container
            PUT: /id/stop - Para um container
        2. /api/images
            GET: lista todas as imagens
***
