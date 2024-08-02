package dev.aman.splitwise.Services;

import dev.aman.splitwise.Models.Expense;
import dev.aman.splitwise.Models.ExpenseUser;
import dev.aman.splitwise.Models.User;
import dev.aman.splitwise.Repositories.ExpenseUserRepository;
import dev.aman.splitwise.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {

    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;

    public SettleUpService(UserRepository userRepository, ExpenseUserRepository expenseUserRepository) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
    }

    public List<Expense> settleUpUser(Long userId) {
        /*
        1. Get the user with the given user id.
        2. Get all the expenses for this user.
        3. Iterate through all the expenses, and find out total extra or lesser amount
        paid by every user involved in the expenses.
        4. Implement min/max Heap algorithm to settle up the user.
         */

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid userId: " + userId);
        }

        User user = optionalUser.get();

        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUser(user);

        Set<Expense> expenses = new HashSet<>();

        for (ExpenseUser expenseUser : expenseUsers) {
            expenses.add(expenseUser.getExpense());
        }
    }

    public List<Expense> settleUpGroup(Long groupId) {
        return null;
    }
}
