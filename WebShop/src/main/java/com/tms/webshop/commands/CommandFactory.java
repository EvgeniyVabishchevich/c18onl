package com.tms.webshop.commands;

import com.tms.webshop.model.enums.Commands;
import com.tms.webshop.model.enums.RequestParams;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;

public class CommandFactory {
    private final static HashMap<String, BaseCommand> commandsMap = new HashMap<>();

    static {
        commandsMap.put(Commands.SIGN_IN.getCommand(), new SignInCommand());
        commandsMap.put(Commands.REGISTER.getCommand(), new RegisterCommand());
        commandsMap.put(Commands.BUY.getCommand(), new BuyCommand());
        commandsMap.put(Commands.ADD_PRODUCT_CART.getCommand(), new AddProductCartCommand());
        commandsMap.put(Commands.CART.getCommand(), new CartCommand());
        commandsMap.put(Commands.IMAGE.getCommand(), new ImageCommand());
        commandsMap.put(Commands.PRODUCTS.getCommand(), new ProductsCommand());
        commandsMap.put(Commands.USER.getCommand(), new UserCommand());
        commandsMap.put(Commands.REMOVE_FROM_CART.getCommand(), new RemoveFromCartCommand());
        commandsMap.put(Commands.NEW_CATEGORY.getCommand(), new NewCategoryCommand());
        commandsMap.put(Commands.NEW_PRODUCT.getCommand(), new NewProductCommand());
    }

    public static BaseCommand defineCommand(HttpServletRequest request) {
        String commandKey = request.getParameter(RequestParams.COMMAND.getValue());

        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = Commands.SIGN_IN.getCommand();
        }
        return commandsMap.get(commandKey);
    }
}
