package ru.osipov.moneytransfer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.osipov.moneytransfer.model.CardsData;
import ru.osipov.moneytransfer.model.ConfirmationData;
import ru.osipov.moneytransfer.model.OperationID;

import static org.junit.jupiter.api.Assertions.*;
import static ru.osipov.moneytransfer.service.MoneyTransferService.isCardOverdue;

@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceTest {
    @Mock
    MoneyTransferService mts;
    @Mock
    CardsData cardsData;

    @Mock
    OperationID operationID;
    @Mock
    ConfirmationData confirmationData;

    @Test
    void testGetOperationId() {
        Mockito.doReturn(operationID).when(mts).getOperationId(cardsData);
        assertEquals(operationID, mts.getOperationId(cardsData));
    }

    @Test
    void testConfirmOperation() {
        Mockito.doReturn(operationID).when(mts).confirmOperation(confirmationData);
        assertEquals(operationID, mts.confirmOperation(confirmationData));
    }

    @Test
    void testIsCardOverdue() {
        assertTrue(isCardOverdue("12/22"));
        assertTrue(isCardOverdue("01/23"));
        assertFalse(isCardOverdue("02/23"));
        assertFalse(isCardOverdue("12/23"));
    }
}