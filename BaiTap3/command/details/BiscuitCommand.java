package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class BiscuitCommand implements ICommand {

    private final DoableFood.IDoableBiscuit chef;

    public BiscuitCommand(DoableFood.IDoableBiscuit chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeBiscuit();
    }
}
