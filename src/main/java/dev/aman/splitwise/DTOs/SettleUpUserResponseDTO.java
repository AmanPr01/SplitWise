package dev.aman.splitwise.DTOs;

import dev.aman.splitwise.Models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDTO {
    private List<Expense> expenses;
}
