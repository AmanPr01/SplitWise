package dev.aman.splitwise.Commands;

import java.util.List;

public class RegisterUserCommand implements Command {

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));

        return words.size() == 4 && words.get(0).equalsIgnoreCase(CommandKeywords.registerCommand);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));


    }
}
