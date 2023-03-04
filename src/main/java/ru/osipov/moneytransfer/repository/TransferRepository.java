package ru.osipov.moneytransfer.repository;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Repository;
import ru.osipov.moneytransfer.model.Amount;
import ru.osipov.moneytransfer.model.ConfirmationData;
import ru.osipov.moneytransfer.model.OperationID;
import ru.osipov.moneytransfer.model.TransferData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepository {
    private final Map<String, TransferData> transfers = new ConcurrentHashMap<>();

    TransferRepository() {
        //создадим псевдооперацию для проверки
        transfers.put("111",
                new TransferData("1111666677778888", "01/25", "111", "2222666677778888", new Amount(200000, "RUR")));
    }

    public OperationID addTransfer(TransferData transferData) {
        OperationID opId = new OperationID(RandomStringUtils.randomNumeric(5)
                + "-" + RandomStringUtils.randomNumeric(10)
                + "-" + RandomStringUtils.randomNumeric(5));
        transfers.put(opId.getOperationId(), transferData);
        return opId;
    }

    public boolean confirmOperation(ConfirmationData confirmationData) {
        return transfers.containsKey(confirmationData.getOperationId());
    }

    public TransferData getTransferDataById(String id) {
        return transfers.get(id);
    }
}