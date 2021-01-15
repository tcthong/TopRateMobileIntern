package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class BurgerCommand implements ICommand {

    private final DoableFood.IDoableBurger chef;

    public BurgerCommand(DoableFood.IDoableBurger chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeBurger();
    }
}
