package com.example.weathear.exception;

public record ErrorResponse(
        String success,
        Error error
) {

}
