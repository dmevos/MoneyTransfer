package ru.osipov.moneytransfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.osipov.moneytransfer.logger.Logger;
import ru.osipov.moneytransfer.model.ConfirmationData;
import ru.osipov.moneytransfer.model.OperationID;
import ru.osipov.moneytransfer.model.TransferData;
import ru.osipov.moneytransfer.service.MoneyTransferService;

@CrossOrigin(origins = "https://serp-ya.github.io")
@RestController
public class MoneyTransferController {
    private final Logger logger = Logger.getInstance();
    MoneyTransferService service;

    public MoneyTransferController(MoneyTransferService service) {
        this.service = service;
    }

    @PostMapping("transfer")
    public ResponseEntity<OperationID> requestToTransfer(@RequestBody TransferData transferData) {
        logger.log("Получили исходные сведения с фронта: " + transferData);

        var tmp = service.getOperationId(transferData);
        logger.log("Controller принял OperationID: " + tmp.toString());
        return ResponseEntity.ok(tmp);
    }

    @PostMapping("confirmOperation")
    public ResponseEntity<OperationID> requestToConfirm(@RequestBody ConfirmationData confirmationData) {
        logger.log("Получили исходные сведения: " + confirmationData.toString());
        var tmp = service.confirmOperation(confirmationData);

        return ResponseEntity.ok(tmp);
    }
}