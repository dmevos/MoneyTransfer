package ru.osipov.moneytransfer.model;

public class Amount {
    int value; //сумма перевода
    String currency; //валюта перевода

    public Amount(int value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}