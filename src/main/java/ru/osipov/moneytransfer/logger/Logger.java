package ru.osipov.moneytransfer.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    private static Logger instance = null;
    final static String LOG_FILE_NAME = "log_MoneyTransfer.log";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    int count;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
            try (FileWriter fw = new FileWriter(LOG_FILE_NAME, true)) {
                fw.write("\nЛОГ РАБОТЫ СЕРВЕРА ПО ПЕРЕВОДУ ДЕНЕГ С КАРТЫ НА КАРТУ:\n");
                System.out.print(ANSI_BLUE + "ЛОГ РАБОТЫ СЕРВЕРА ПО ПЕРЕВОДУ ДЕНЕГ С КАРТЫ НА КАРТУ:\n" + ANSI_RESET);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return instance;
    }

    public void log(String msg) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_NAME, true)) {
            fileWriter.write(++count + ". [" + new Date() + "] " + msg + "\n");
            System.out.println(ANSI_BLUE + count + ". [" + new Date() + "] " + msg + ANSI_RESET);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}