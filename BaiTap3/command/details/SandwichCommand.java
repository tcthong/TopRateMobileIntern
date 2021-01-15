package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class SandwichCommand implements ICommand {

    private final DoableFood.IDoableSandwich chef;

    public SandwichCommand(DoableFood.IDoableSandwich chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeSandwich();
    }
}
