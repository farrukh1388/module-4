package com.jmp.reactive.workshop.service;

import com.jmp.reactive.workshop.client.SportsApiClient;
import com.jmp.reactive.workshop.repository.SportsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SportsApiConsumer implements SmartLifecycle {

    private final SportsRepository repository;
    private final SportsApiClient apiClient;

    @Override
    public void start() {
        repository.deleteAll().block();
        repository.insert(apiClient.getAllSports().log().limitRate(20)).blockLast();
    }

    @Override
    public void stop() {
        log.info("Stopping consumer");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
