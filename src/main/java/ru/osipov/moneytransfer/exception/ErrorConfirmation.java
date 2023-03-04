package ru.osipov.moneytransfer.exception;
public class ErrorConfirmation extends RuntimeException {
    public ErrorConfirmation(String str) {
        super(str);
    }
}