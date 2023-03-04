package ru.osipov.moneytransfer.model;

public class ErrorClass {
    String message;
    int id;

    public ErrorClass(String message, int id) {
        this.message = message;
        this.id = id;
    }
}