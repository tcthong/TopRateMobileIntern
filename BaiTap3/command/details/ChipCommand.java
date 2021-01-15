package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class ChipCommand implements ICommand {

    private final DoableFood.IDoableChip chef;

    public ChipCommand(DoableFood.IDoableChip chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeChip();
    }
}
