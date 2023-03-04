package ru.osipov.moneytransfer.model;

public class OperationID {
    String operationId;

    public OperationID(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public String toString() {
        return "OperationId{" +
                "operationId='" + operationId + '\'' +
                '}';
    }
}