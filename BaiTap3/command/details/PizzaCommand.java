package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class PizzaCommand implements ICommand {

    private final DoableFood.IDoablePizza chef;

    public PizzaCommand(DoableFood.IDoablePizza chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makePizza();
    }
}
