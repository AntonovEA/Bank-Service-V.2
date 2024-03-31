import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class BankAccountController {

    private int balance;
    private boolean success;
    private List<String> operationsList;
    private UserService userService;

    private final DatabaseService databaseService; // Сервис для работы с базой данных

    @Autowired
    public BankAccountController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
    public BankAccountController(UserService userService, DatabaseService databaseService) {
        this.userService = userService;
        this.databaseService = databaseService;
    }

    @GetMapping("/balance/{userId}")
    public String getBalance(@PathVariable int userId) {
        // Логика получения баланса пользователя по ID
        // Добавьте вашу логику здесь
        return "Текущий баланс: " + balance;
    }


    @GetMapping("/operationsList/{userId}")
    public List<String> getOperationsList(@PathVariable int userId) {
        if (operationsList == null) {
            // Получаем список операций из базы данных при первом обращении
            operationsList = databaseService.getOperationsForUser(userId);
        }
        return operationsList;
    }


    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable int userId) {
        double balance = userService.getBalance(userId); // Предполагается, что есть сервис userService, который возвращает баланс пользователя из базы данных
        return ResponseEntity.ok(balance);
    }



    @PostMapping("/takeMoney/{userId}")
    public ResponseEntity<String> takeMoney(@PathVariable int userId, @RequestParam double amount) {
        boolean success = userService.takeMoney(userId, amount); // Предполагается, что есть сервис userService, который осуществляет снятие средств с баланса пользователя
        if (success) {
            return ResponseEntity.ok("Сумма успешно снята");
        } else {
            return ResponseEntity.badRequest().body("Недостаточно средств");
        }
    }


    @PostMapping("/addMoney/{userId}")
    public ResponseEntity<String> addMoney(@PathVariable int userId, @RequestParam double amount) {
        boolean success = userService.addMoney(userId, amount); // Предполагается, что есть сервис userService, который осуществляет пополнение баланса пользователя
        if (success) {
            return ResponseEntity.ok("Баланс успешно пополнен");
        } else {
            return ResponseEntity.badRequest().body("Ошибка при пополнении баланса");
        }
    }


    @GetMapping("/operationsList/{userId}")
    public ResponseEntity<List<String>> getOperationsList(@PathVariable int userId, @RequestParam String startDate, @RequestParam String endDate) {
        List<String> operationsList = userService.getOperationsList(userId, startDate, endDate); // Предполагается, что есть сервис userService, который возвращает список операций пользователя за указанный период из базы данных
        return ResponseEntity.ok(operationsList);
    }


    @PostMapping("/transferMoney")
    public ResponseEntity<String> transferMoney(@RequestParam int senderId, @RequestParam int recipientId, @RequestParam double amount) {
        boolean success = userService.transferMoney(senderId, recipientId, amount); // Предполагается, что есть сервис userService, который осуществляет перевод средств между пользователями
        if (success) {
            return ResponseEntity.ok("Перевод успешно выполнен");
        } else {
            return ResponseEntity.badRequest().body("Недостаточно средств для перевода");
        }
    }

}