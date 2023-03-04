package ru.osipov.moneytransfer.model;

public class Card {
    private final String number;
    private final String validTill;
    private final String cvv;

    public Card(String number, String validTill, String cvv) {
        this.number = number;
        this.validTill = validTill;
        this.cvv = cvv;
    }

    public String getValidTill() {
        return validTill;
    }

    public String getCvv() {
        return cvv;
    }

    public String getNumber() {
        return number;
    }
}