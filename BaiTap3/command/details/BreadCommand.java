package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class BreadCommand implements ICommand {

    private final DoableFood.IDoableBread chef;

    public BreadCommand(DoableFood.IDoableBread chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeBread();
    }
}
