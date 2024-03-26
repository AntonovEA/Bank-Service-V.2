package Controller;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    private int balance;
    private boolean success;
    private List<String> operationsList;

    private final DatabaseService databaseService; // Сервис для работы с базой данных

    @Autowired
    public BankAccountController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/balance/{userId}")
    public int getBalance(@PathVariable int userId) {
        if (balance == 0) {
            // Получаем баланс из базы данных при первом обращении
            balance = databaseService.getBalanceForUser(userId);
        }
        return balance;
    }

    @GetMapping("/operationsList/{userId}")
    public List<String> getOperationsList(@PathVariable int userId) {
        if (operationsList == null) {
            // Получаем список операций из базы данных при первом обращении
            operationsList = databaseService.getOperationsForUser(userId);
        }
        return operationsList;
    }
    public BankAccountController() {
    }

    @GetMapping("/balance/{userId}")
    public String getBalance(@PathVariable int userId) {
        // Логика получения баланса пользователя по ID
        return "Текущий баланс: " + balance;
    }

    @PostMapping("/takeMoney/{userId}")
    public String takeMoney(@PathVariable int userId, @RequestParam int amount) {
        // Логика снятия заданной суммы с баланса пользователя
        if (success) {
            return "Сумма успешно снята";
        } else {
            return "Недостаточно средств";
        }
    }

    @PostMapping("/addMoney/{userId}")
    public String addMoney(@PathVariable int userId, @RequestParam int amount) {
        // Логика пополнения баланса пользователя
        if (success) {
            return "Баланс успешно пополнен";
        } else {
            return "Ошибка при пополнении баланса";
        }
    }

    @GetMapping("/operationsList/{userId}")
    public String getOperationsList(@PathVariable int userId, @RequestParam String startDate, @RequestParam String endDate) {
        // Логика получения списка операций за выбранный период
        return "Список операций: " + operationsList;
    }

    @PostMapping("/transferMoney")
    public String transferMoney(@RequestParam int senderId, @RequestParam int recipientId, @RequestParam int amount) {
        // Логика перевода заданной суммы от отправителя к получателю
        if (success) {
            return "Перевод успешно выполнен";
        } else {
            return "Недостаточно средств для перевода";
        }
    }
}