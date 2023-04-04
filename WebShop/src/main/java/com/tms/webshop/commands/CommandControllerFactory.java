package com.tms.webshop.commands;

import com.tms.webshop.model.enums.Command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class CommandControllerFactory {
    private static final Map<String, BaseCommandController> commandsMap = new ConcurrentHashMap<>();

    public static BaseCommandController defineCommand(Command command) {
        return putIfAbsent(command.getCommand(), createController(command));
    }

    private static Supplier<BaseCommandController> createController(Command command) {
        return switch (command) {
            case SIGN_IN -> SignInController::new;
            case REGISTER -> RegisterController::new;
            case BUY -> BuyController::new;
            case ADD_PRODUCT_CART -> AddProductCartController::new;
            case CART -> CartController::new;
            case IMAGE -> ImageController::new;
            case PRODUCTS -> ProductsController::new;
            case USER -> UserController::new;
            case REMOVE_FROM_CART -> RemoveFromCartController::new;
            case NEW_CATEGORY -> NewCategoryController::new;
            case NEW_PRODUCT -> NewProductController::new;
        };
    }

    private static BaseCommandController putIfAbsent(String key, Supplier<BaseCommandController> supplier) {
        BaseCommandController commandController = commandsMap.get(key);
        if (commandController == null) {
            commandController = supplier.get();
            commandsMap.put(key, commandController);
        }
        return commandController;
    }
}
