package ru.osipov.moneytransfer.exception;

import ru.osipov.moneytransfer.model.CardsData;

public class ErrorTransfer extends RuntimeException {
    public ErrorTransfer(CardsData ctcInfo) {
        super(ctcInfo.toString());
    }
}