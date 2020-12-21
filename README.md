# Marvel-API
> Replica da [API da Marvel](https://developer.marvel.com/docs#!/public)

Projeto que replica a API da Marvel. Usando banco de dados H2, populando as informações com um arquivo data.sql, trabalhando também de forma offline.


## Instalação e Execução

Docker:

```sh
mvn clean package
docker build -t marvel .
docker run -p 9190:9190 marvel
```

Console/Jar:

```sh
cd .\target\
mvn clean package
java -jar marvel-0.0.1-SNAPSHOT.jar
```


## Utilização

Ao instalar e executar o projeto, ele estará disponível na url `http://localhost:9190`.

Para verificar se o projeto está disponível faça uma requisição para a url `http://localhost:9190/actuator/health`.

Os endpoints disponíveis são:

- /v1/public/characters
- /v1/public/characters/{characterId}
- /v1/public/characters/{characterId}/comics
- /v1/public/characters/{characterId}/events
- /v1/public/characters/{characterId}/series
- /v1/public/characters/{characterId}/stories


## Documentação

Para ver a documentação, ela está disponível na url: `http://localhost:9190/swagger-ui.html`
