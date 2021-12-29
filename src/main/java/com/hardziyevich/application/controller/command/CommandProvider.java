package com.hardziyevich.application.controller.command;

import com.hardziyevich.application.controller.command.impl.error.ErrorCommand;
import com.hardziyevich.application.controller.command.impl.RegistrationCommand;
import com.hardziyevich.application.controller.command.impl.go.GoAppCommand;
import com.hardziyevich.application.controller.command.impl.go.GoRegistrationCommand;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Optional;

import static com.hardziyevich.application.controller.command.CommandType.*;

public enum CommandProvider {
    INSTANCE;
    private final EnumMap<CommandType,Command> warehouse;

    CommandProvider() {
        warehouse = new EnumMap<>(CommandType.class);
        warehouse.put(GO_APP,new GoAppCommand());
        warehouse.put(GO_REGISTRATION,new GoRegistrationCommand());
        warehouse.put(REGISTRATION,new RegistrationCommand());
    }

    public Command getCommand(String param) {
        Command command;
        Optional<CommandType> first = Arrays.stream(CommandType.values()).filter(c -> c.toString().equals(param)).findFirst();
        if(first.isEmpty()){
            command = new ErrorCommand();
        } else {
            command = warehouse.get(first.get());
        }
        return command;
    }

}
