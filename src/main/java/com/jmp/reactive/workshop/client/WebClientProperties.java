package com.jmp.reactive.workshop.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "web-client")
public class WebClientProperties {

    private String baseUrl;
}
