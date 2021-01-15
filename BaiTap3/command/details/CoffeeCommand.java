package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class CoffeeCommand implements ICommand {

    private final DoableFood.IDoableCoffee chef;

    public CoffeeCommand(DoableFood.IDoableCoffee chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeCoffee();
    }
}
