package dev.aman.splitwise;

import dev.aman.splitwise.Commands.CommandExecuter;
import dev.aman.splitwise.Commands.SettleUpUserCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitWiseApplication implements CommandLineRunner {

    private CommandExecuter commandExecuter;

    public SplitWiseApplication (CommandExecuter commandExecuter) {
        this.commandExecuter = commandExecuter;
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();
            commandExecuter.execute(input);
        }
    }
}
