package ru.osipov.moneytransfer.repository;

import org.springframework.stereotype.Repository;
import ru.osipov.moneytransfer.model.Card;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepository {
    private final Map<String, Card> cards = new ConcurrentHashMap<>();

    {
        cards.put("1111666677778888", new Card("1111666677778888", "01/25", "111"));
        cards.put("2222666677778888", new Card("2222666677778888", "02/25", "222"));
        cards.put("3333666677778888", new Card("3333666677778888", "03/25", "333"));

        //Добавлю карту с косячным номером
        cards.put("4444666677778888", new Card("444466667777888", "04/25", "444"));

        //Добавлю просроченную карту
        cards.put("5555666677778888", new Card("5555666677778888", "05/22", "555"));
    }

    public Optional<Card> getCardByNumber(String cardNumber) {
        return Optional.ofNullable(cards.get(cardNumber));
    }
}