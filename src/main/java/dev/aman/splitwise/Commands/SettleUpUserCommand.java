package dev.aman.splitwise.Commands;

import dev.aman.splitwise.Controllers.SettleUpController;
import dev.aman.splitwise.DTOs.SettleUpUserRequestDTO;
import dev.aman.splitwise.DTOs.SettleUpUserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUserCommand implements Command {

    private SettleUpController settleUpController;

    public SettleUpUserCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        // input string :- 1234 settleup
        // words --> {1234, settleup}
        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(1).equalsIgnoreCase(CommandKeywords.settleUpCommand);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));

        Long userId = Long.valueOf(words.get(0));

        SettleUpUserRequestDTO requestDTO = new SettleUpUserRequestDTO();
        requestDTO.setUserId(userId);

        SettleUpUserResponseDTO responseDTO = settleUpController.settleUpUser(requestDTO);

    }
}
