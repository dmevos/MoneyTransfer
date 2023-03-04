package ru.osipov.moneytransfer.exception;
public class ErrorTransfer extends RuntimeException {
    public ErrorTransfer(String str) {
        super(str);
    }
}