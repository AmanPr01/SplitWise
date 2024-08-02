package dev.aman.splitwise.Strategies;

import dev.aman.splitwise.Models.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy {

    @Override
    public List<Expense> settleUp(List<Expense> expenses) {
        // DSA problem
        return List.of();
    }
}
