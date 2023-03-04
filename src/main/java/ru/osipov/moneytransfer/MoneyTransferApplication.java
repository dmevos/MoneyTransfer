package ru.osipov.moneytransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.osipov.moneytransfer.logger.Logger;

@SpringBootApplication
public class MoneyTransferApplication {
    static final Logger logger = Logger.getInstance();

    public static void main(String[] args) {
        logger.log("Запускается MoneyTransferApplication");
        SpringApplication.run(MoneyTransferApplication.class, args);
    }
}