package ru.osipov.moneytransfer.model;

public class ErrorClass {
    public ErrorClass() {
    }

    public ErrorClass(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String message;
    int id;
}