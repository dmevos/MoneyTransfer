package ru.osipov.moneytransfer.exception;

public class ErrorInputData extends RuntimeException {
    public ErrorInputData(String str) {
        super(str);
    }
}