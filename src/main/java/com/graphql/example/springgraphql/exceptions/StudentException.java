package com.graphql.example.springgraphql.exceptions;


public class StudentException extends RuntimeException {
    public StudentException(String message) {
        super(message);
    }
}
