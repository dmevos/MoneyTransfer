package ru.osipov.moneytransfer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.osipov.moneytransfer.logger.Logger;
import ru.osipov.moneytransfer.model.CardsData;
import ru.osipov.moneytransfer.model.ConfirmationData;
import ru.osipov.moneytransfer.model.OperationID;
import ru.osipov.moneytransfer.service.MoneyTransferService;

@CrossOrigin(origins = "https://serp-ya.github.io")
@RestController
//@RequestMapping("transfer")
public class MoneyTransferController {
    private final Logger logger = Logger.getInstance();
    MoneyTransferService service;
    public static final String ANSI_RED = "\u001B[31m";
    private String fromCard;
    private String toCard;
    private int value;

    public MoneyTransferController(MoneyTransferService service) {
        this.service = service;
    }


    @PostMapping("transfer")
    public OperationID requestToTransfer(@RequestBody CardsData cardsData) {
        logger.log("Получили исходные сведения с фронта: " + cardsData.toString());

        fromCard = cardsData.getCardFromNumber();
        toCard = cardsData.getCardToNumber();
        value = cardsData.getAmount().getValue();

        var tmp = service.getOperationId(cardsData);
        logger.log("Controller принял OperationID: " + tmp.toString());
        return tmp;
    }

    @PostMapping("confirmOperation")
    public OperationID requestToConfirm(@RequestBody ConfirmationData confirmationData) {
        logger.log("Получили исходные сведения: " + confirmationData.toString());
        var tmp = service.confirmOperation(confirmationData);
        logger.log("Controller принял подтвержденный OperationID: " + tmp.toString());
        logger.log(ANSI_RED + "Перевод " + value / 100 + " рублей с карты " + fromCard + " на карту " + toCard
                + " произведен успешно. Была взята комиссия в размере " + ((double) value) / 10000 + " рублей");
        return tmp;
    }
}