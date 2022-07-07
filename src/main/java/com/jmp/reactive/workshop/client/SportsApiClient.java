package com.jmp.reactive.workshop.client;

import com.jmp.reactive.workshop.model.Sports;
import reactor.core.publisher.Flux;

public interface SportsApiClient {

    Flux<Sports> getAllSports();
}
