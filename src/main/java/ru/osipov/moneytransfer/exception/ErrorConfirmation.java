package ru.osipov.moneytransfer.exception;

import ru.osipov.moneytransfer.model.ConfirmationData;

public class ErrorConfirmation extends RuntimeException {

    public ErrorConfirmation(ConfirmationData confirmationData) {
        super(confirmationData.toString());
    }


}