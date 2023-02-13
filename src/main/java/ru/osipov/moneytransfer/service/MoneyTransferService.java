package ru.osipov.moneytransfer.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import ru.osipov.moneytransfer.exception.ErrorConfirmation;
import ru.osipov.moneytransfer.exception.ErrorInputData;
import ru.osipov.moneytransfer.exception.ErrorTransfer;
import ru.osipov.moneytransfer.logger.Logger;
import ru.osipov.moneytransfer.model.CardsData;
import ru.osipov.moneytransfer.model.ConfirmationData;
import ru.osipov.moneytransfer.model.OperationID;

import java.util.Calendar;

@Service
public class MoneyTransferService {
    private final Logger logger = Logger.getInstance();
    private OperationID opId;


    final String CODE = "0000"; //Это проверочный код. Будем сверять в confirmOperation

    public MoneyTransferService() {
    }

    public OperationID getOperationId(CardsData cardsData) {
        logger.log("Service принял данные карт: " + cardsData.toString());
     /*   Хоть на фронте и стоит проверка отправки валидных значений, все равно проверим.
        Вдруг на сервере будут еще какие нибудь дополнительные критерии */
        if (isEmpty(cardsData.getCardFromNumber()) || isEmpty(cardsData.getCardFromCVV()) ||
                isEmpty(cardsData.getCardFromValidTill()) || isEmpty(cardsData.getCardToNumber())) {
            throw new ErrorInputData(cardsData.toString());
        }

        if (isCardOverdue(cardsData.getCardFromValidTill())) throw new ErrorInputData(cardsData.toString());

        //Глупая проверка в этом месте этого никогда не произойдет
        if (cardsData == null) throw new ErrorTransfer(null);

        opId = new OperationID(RandomStringUtils.randomNumeric(5)
                + "-" + RandomStringUtils.randomNumeric(10)
                + "-" + RandomStringUtils.randomNumeric(5));
        logger.log("Service создал OperationID: " + opId.toString());
        return opId;
    }

    public static boolean isCardOverdue(String cardData) {
        //получим два последних символа строки, т.е. год
        var yearOnCard = Integer.parseInt(cardData.substring(cardData.length() - 2));
        var yearOnNow = Calendar.getInstance().get(Calendar.YEAR) - 2000;

        if (yearOnNow > yearOnCard) return true;

        if (yearOnNow == yearOnCard) {
            var monthOnCard = Integer.parseInt(cardData.substring(0,2));
            var monthOnNow = Calendar.getInstance().get(Calendar.MONTH) + 1;
            return monthOnNow > monthOnCard;
        }
        return false;
    }

    public OperationID confirmOperation(ConfirmationData confirmationData) {
        logger.log("Service принял confirmationData: " + confirmationData.toString());

        if (!(opId.getOperationId().equals(confirmationData.getOperationId()) &&
                CODE.equals(confirmationData.getCode())))
            throw new ErrorConfirmation(confirmationData);

        if (isEmpty(confirmationData.getCode()) || isEmpty(confirmationData.getOperationId()))
            throw new ErrorInputData(confirmationData.toString());

        var tmp = new OperationID(confirmationData.getOperationId());
        logger.log("Service подтверждает OperationID: " + opId.toString());
        return tmp;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}