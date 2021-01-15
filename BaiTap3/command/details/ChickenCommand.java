package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class ChickenCommand implements ICommand {

    private final DoableFood.IDoableChicken chef;

    public ChickenCommand(DoableFood.IDoableChicken chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeChicken();
    }
}
