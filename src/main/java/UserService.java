import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public double getBalance(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        return (user != null) ? user.getBalance() : 0.0;
    }

    public boolean takeMoney(int userId, double amount) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean addMoney(int userId, double amount) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<String> getOperationsList(int userId, String startDate, String endDate) {
        // Логика получения списка операций пользователя за указанный период
        // Например, запрос к базе данных через userRepository для получения списка операций
        return null; // Вернуть список операций или null, в зависимости от реализации
    }

    public boolean transferMoney(int senderId, int recipientId, double amount) {
        User sender = userRepository.findById(senderId).orElse(null);
        User recipient = userRepository.findById(recipientId).orElse(null);
        if (sender != null && recipient != null && sender.getBalance() >= amount) {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            userRepository.save(sender);
            userRepository.save(recipient);
            return true;
        }
        return false;
    }
}
