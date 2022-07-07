package com.jmp.reactive.workshop.api;

import com.jmp.reactive.workshop.model.Sports;
import com.jmp.reactive.workshop.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sports")
@RequiredArgsConstructor
public class SportsController {

    private final SportsRepository repository;

    @GetMapping
    public Flux<Sports> getAllSports() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Integer id) {
        return repository.deleteById(id);
    }
}
