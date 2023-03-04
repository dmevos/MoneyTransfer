package ru.osipov.moneytransfer.model;

public class ConfirmationData {
    String operationId;
    String code;

    public String getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ConfirmationData{" +
                "operationID='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}