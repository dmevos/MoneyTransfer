package ru.osipov.moneytransfer.service;

import org.springframework.stereotype.Service;
import ru.osipov.moneytransfer.exception.ErrorConfirmation;
import ru.osipov.moneytransfer.exception.ErrorInputData;
import ru.osipov.moneytransfer.exception.ErrorTransfer;
import ru.osipov.moneytransfer.logger.Logger;
import ru.osipov.moneytransfer.model.Card;
import ru.osipov.moneytransfer.model.ConfirmationData;
import ru.osipov.moneytransfer.model.OperationID;
import ru.osipov.moneytransfer.model.TransferData;
import ru.osipov.moneytransfer.repository.CardRepository;
import ru.osipov.moneytransfer.repository.TransferRepository;

import java.util.Calendar;
import java.util.Objects;

@Service
public class MoneyTransferService {
    private final Logger logger = Logger.getInstance();
    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;
    public static final String ANSI_RED = "\u001B[31m";

    final String CODE = "0000"; //Это проверочный код. Будем сверять в confirmOperation

    public MoneyTransferService(CardRepository cardRepository, TransferRepository transferRepository) {
        this.cardRepository = cardRepository;
        this.transferRepository = transferRepository;
    }

    public OperationID getOperationId(TransferData transferData) {
        logger.log("Service принял данные карт: " + transferData.toString());

        var cardFromNum = transferData.getCardFromNumber();
        Card existingCardFrom = cardRepository.getCardByNumber(cardFromNum).orElseThrow(
                () -> new ErrorTransfer("Карта с номером " + cardFromNum + " не найдена")
                //по идее можно добавлять эту карту в репозиторий, но я считаю,
                // что это совсем не обязательно при решении поставленной задачи
        );

        var validTillIsCorrect = Objects.equals(existingCardFrom.getValidTill(), transferData.getCardFromValidTill());
        var cvvIsCorrect = Objects.equals(existingCardFrom.getCvv(), transferData.getCardFromCVV());
        if (!validTillIsCorrect || !cvvIsCorrect) {
            throw new ErrorInputData("Введены неверные данные карты (срок действия / CVV номер)");
        }

        if (isCardOverdue(existingCardFrom.getValidTill()))
            throw new ErrorInputData("Карта с номером " + cardFromNum + " просрочена");

        logger.log("Все данные карт корректны. Будем создавать OperationId...");

        return transferRepository.addTransfer(transferData);
    }

    public OperationID confirmOperation(ConfirmationData confirmationData) {
        logger.log("Service принял confirmationData: " + confirmationData.toString());

        var opId = confirmationData.getOperationId();

        if (!transferRepository.confirmOperation(confirmationData))
            throw new ErrorConfirmation("Опереция с ID: " + opId + " отсутствует");

        if (!Objects.equals(CODE, confirmationData.getCode()))
            throw new ErrorConfirmation("Неверный код подтверждения");

        if (isEmpty(confirmationData.getCode()) || isEmpty(opId))
            throw new ErrorInputData(confirmationData.toString());

        var tmp = new OperationID(opId);
        var transfer = transferRepository.getTransferDataById(opId);
        var tmpStr = String.format(ANSI_RED + "Перевод %s рублей с карты %s на карту %s произведен успешно. " +
                        "Была взята комиссия в размере %s рублей\n",
                transfer.getAmount().getValue() / 100,
                transfer.getCardFromNumber(),
                transfer.getCardToNumber(),
                transfer.getAmount().getValue() / 10000
        );
        logger.log(tmpStr);

        return tmp;
    }

    public static boolean isCardOverdue(String cardData) {
        //получим два последних символа строки, т.е. год
        var yearOnCard = Integer.parseInt(cardData.substring(cardData.length() - 2));
        var yearOnNow = Calendar.getInstance().get(Calendar.YEAR) - 2000;

        if (yearOnNow > yearOnCard) return true;

        if (yearOnNow == yearOnCard) {
            var monthOnCard = Integer.parseInt(cardData.substring(0, 2));
            var monthOnNow = Calendar.getInstance().get(Calendar.MONTH) + 1;
            return monthOnNow > monthOnCard;
        }
        return false;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}