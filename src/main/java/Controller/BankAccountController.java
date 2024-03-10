package Controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    private int balance;
    private boolean success;
    private ArrayList operationsList;

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