package ru.osipov.moneytransfer.model;

public class ConfirmationData {
    String operationId;
    String code;

    public ConfirmationData() {
    }

    public ConfirmationData(String operationID, String code) {
        this.operationId = operationID;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ConfirmationData{" +
                "operationID='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}