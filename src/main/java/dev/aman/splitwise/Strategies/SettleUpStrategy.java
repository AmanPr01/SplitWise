package dev.aman.splitwise.Strategies;

import dev.aman.splitwise.Models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<Expense> settleUp(List<Expense> expenses);
}
