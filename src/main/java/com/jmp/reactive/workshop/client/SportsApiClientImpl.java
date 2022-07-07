package com.jmp.reactive.workshop.client;

import com.jmp.reactive.workshop.model.Sports;
import com.jmp.reactive.workshop.model.SportsData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class SportsApiClientImpl implements SportsApiClient {

    private final WebClient webClient;

    @Override
    public Flux<Sports> getAllSports() {
        return webClient.get()
                .uri("/sports")
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(SportsData.class))
                .doOnError(error -> log.error("Error getting response", error))
                .flatMapIterable(SportsData::getData)
                .doOnNext(sports -> log.info("Sports: {}", sports));
    }
}
