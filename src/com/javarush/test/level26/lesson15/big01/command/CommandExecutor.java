package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Polurival on 21.02.2016.
 */
public class CommandExecutor
{
    private static Map<Operation, Command> commands = new HashMap<>();

    private CommandExecutor() {}

    static {
        commands.put(Operation.INFO, new InfoCommand());
        commands.put(Operation.DEPOSIT, new DepositCommand());
        commands.put(Operation.WITHDRAW, new WithdrawCommand());
        commands.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) {
        commands.get(operation).execute();
    }
}
