package com.jmp.reactive.workshop.api.function;

import com.jmp.reactive.workshop.model.Sports;
import com.jmp.reactive.workshop.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class SportsApiHandler {

    private final SportsRepository repository;
    private final SecureRandom random = new SecureRandom();

    public Mono<ServerResponse> getById(ServerRequest request) {
        var id =
                Optional.of(request.pathVariable("id"))
                        .filter(Predicate.not(String::isBlank))
                        .orElseThrow(() -> new ServerWebInputException("invalid id parameter"));

        return repository
                .findById(Integer.valueOf(id))
                .flatMap(sports -> ServerResponse.ok().bodyValue(sports))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getByName(ServerRequest request) {
        var name =
                Optional.of(request.pathVariable("name"))
                        .filter(Predicate.not(String::isBlank))
                        .orElseThrow(() -> new ServerWebInputException("invalid name parameter"));

        return repository
                .findByName(name)
                .flatMap(sports -> ServerResponse.ok().bodyValue(sports))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createByName(ServerRequest request) {
        var name =
                Optional.of(request.pathVariable("name"))
                        .filter(Predicate.not(String::isBlank))
                        .orElseThrow(() -> new ServerWebInputException("invalid name parameter"));

        return repository
                .findByName(name)
                .flatMap(sports -> ServerResponse.badRequest().bodyValue("Sports with this type already exists"))
                .switchIfEmpty(repository
                        .save(new Sports(random.nextInt(1000) + 1000, name, Map.of("name", name)))
                        .flatMap(sports -> ServerResponse.ok().bodyValue(sports)));
    }
}
