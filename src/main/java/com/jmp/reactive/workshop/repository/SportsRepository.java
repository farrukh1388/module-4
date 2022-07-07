package com.jmp.reactive.workshop.repository;

import com.jmp.reactive.workshop.model.Sports;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SportsRepository extends ReactiveMongoRepository<Sports, Integer> {

    default Mono<Sports> findByName(String name) {
        return findAll()
                .filter(sports -> sports.getAttributes() != null && sports.getAttributes().get("name") != null)
                .filter(sports -> name.equalsIgnoreCase(sports.getAttributes().get("name").toString())).singleOrEmpty();
    }
}