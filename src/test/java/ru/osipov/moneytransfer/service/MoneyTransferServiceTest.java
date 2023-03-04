package ru.osipov.moneytransfer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.osipov.moneytransfer.model.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.osipov.moneytransfer.service.MoneyTransferService.isCardOverdue;

@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceTest {

    @Mock
    MoneyTransferService moneyTransferService;
    @Mock
    TransferData transferData;

    @Mock
    OperationID operationID;
    @Mock
    ConfirmationData confirmationData;

    @Test
    void testGetOperationId() {
        Mockito.doReturn(operationID).when(moneyTransferService).getOperationId(transferData);
        assertEquals(operationID, moneyTransferService.getOperationId(transferData));
    }

    @Test
    void testConfirmOperation() {
        Mockito.doReturn(operationID).when(moneyTransferService).confirmOperation(confirmationData);
        assertEquals(operationID, moneyTransferService.confirmOperation(confirmationData));
    }

    @Test
    void testIsCardOverdue() {
        assertTrue(isCardOverdue("12/22"));
        assertTrue(isCardOverdue("01/23"));
        assertFalse(isCardOverdue("02/23"));
        assertFalse(isCardOverdue("12/23"));
    }
}