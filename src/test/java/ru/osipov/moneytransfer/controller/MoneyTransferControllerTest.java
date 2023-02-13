package ru.osipov.moneytransfer.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.osipov.moneytransfer.model.CardsData;
import ru.osipov.moneytransfer.model.ConfirmationData;
import ru.osipov.moneytransfer.model.OperationID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MoneyTransferControllerTest {

    @Mock
    CardsData cardsData;
    @Mock
    MoneyTransferController mtc;
    @Mock
    OperationID operationID;
    @Mock
    ConfirmationData confirmationData;

    @Test
    void requestToTransfer() {
        Mockito.doReturn(operationID).when(mtc).requestToTransfer(cardsData);
        assertEquals(operationID, mtc.requestToTransfer(cardsData));
    }

    @Test
    void requestToConfirm() {
        Mockito.doReturn(operationID).when(mtc).requestToConfirm(confirmationData);
        assertEquals(operationID, mtc.requestToConfirm(confirmationData));
    }
}