package com.jmp.reactive.workshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Sports {

    private Integer id;
    private String type;
    private Map<String, Object> attributes;
}
