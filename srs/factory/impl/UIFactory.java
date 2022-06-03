package factory.impl;

import factory.Factory;
import model.pieces.impl.Bishop;
import model.pieces.impl.King;
import userInterface.UserInterface;
import userInterface.impl.ConsoleUI;
import userInterface.impl.WindowUI;
import utils.enums.ColorEnum;

public class UIFactory extends Factory {
    private static UIFactory instance;

    private UIFactory() {}

    public static UIFactory getInstance() {
        if(instance == null)
            instance = new UIFactory();
        return instance;
    }

    @Override
    public final UserInterface build(String type) {
        return switch (type) {
            case "window" -> new WindowUI();
            default -> new ConsoleUI();
        };
    }
}
