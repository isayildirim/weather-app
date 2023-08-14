package com.example.weathear.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Current(
        Integer temperature
) {

}