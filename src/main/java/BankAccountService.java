import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    public BankAccount getBalance(String userId) {
        // реализация получения баланса пользователя
    }

    public BankAccount withdraw(String userId, double amount) {
        // реализация снятия средств со счета пользователя
    }

    public BankAccount deposit(String userId, double amount) {
        // реализация пополнения счета пользователя
    }

    public List<Transaction> getTransactions(String userId, String fromDate, String toDate) {
        // реализация получения списка операций за выбранный период
    }

    public Transaction transfer(String senderUserId, String receiverUserId, double amount) {
        // реализация перевода средств между пользователями
    }
}