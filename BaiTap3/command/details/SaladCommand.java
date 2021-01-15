package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class SaladCommand implements ICommand {

    private final DoableFood.IDoableSalad salad;

    public SaladCommand(DoableFood.IDoableSalad salad) {
        this.salad = salad;
    }

    @Override
    public void execute() {
        salad.makeSalad();
    }
}
