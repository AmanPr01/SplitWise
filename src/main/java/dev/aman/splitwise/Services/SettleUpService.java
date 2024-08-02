package dev.aman.splitwise.Services;

import dev.aman.splitwise.Models.Expense;
import dev.aman.splitwise.Models.ExpenseUser;
import dev.aman.splitwise.Models.Group;
import dev.aman.splitwise.Models.User;
import dev.aman.splitwise.Repositories.ExpenseRepository;
import dev.aman.splitwise.Repositories.ExpenseUserRepository;
import dev.aman.splitwise.Repositories.GroupRepository;
import dev.aman.splitwise.Repositories.UserRepository;
import dev.aman.splitwise.Strategies.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {

    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;

    public SettleUpService(UserRepository userRepository,
                           ExpenseUserRepository expenseUserRepository,
                           SettleUpStrategy settleUpStrategy,
                           GroupRepository groupRepository,
                           ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
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

        // Settle up the expenses.
        List<Expense> settleUpExpenses = settleUpStrategy.settleUp(expenses.stream().toList());

        /*
        A --> B : 200
        A --> C : 700
        B --> C : 500
         */

        List<Expense> expensesToReturn = new ArrayList<>();

        for (Expense expense : settleUpExpenses) {
            for (ExpenseUser expenseUser : expense.getExpenseUsers()) {
                if (expenseUser.getUser().equals(user)) {
                    expensesToReturn.add(expense);
                    break;
                }
            }
        }

        return expensesToReturn;
    }

    public List<Expense> settleUpGroup(Long groupId) {

        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if (optionalGroup.isEmpty()) {
            throw new RuntimeException("Invalid groupId: " + groupId);
        }

        // Find all the expenses for that group.
        // select * from expenses where groupid = 123.
        List<Expense> expenses = expenseRepository.findAllByGroup(optionalGroup.get());

        // Settle Up Algorithm
        return settleUpStrategy.settleUp(expenses);

    }
}
