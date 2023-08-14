package com.example.weathear.dto;

public record Request(
        String type,
        String query,
        String language,
        String unit
){

}
