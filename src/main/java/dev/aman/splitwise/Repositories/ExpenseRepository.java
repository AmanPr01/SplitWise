package dev.aman.splitwise.Repositories;

import dev.aman.splitwise.Models.Expense;
import dev.aman.splitwise.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByGroup(Group group);
}
