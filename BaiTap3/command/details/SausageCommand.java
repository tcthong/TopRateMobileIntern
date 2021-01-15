package bt3.command.details;

import bt3.DoableFood;
import bt3.command.ICommand;

public class SausageCommand implements ICommand {

    private final DoableFood.IDoableSausage chef;

    public SausageCommand(DoableFood.IDoableSausage chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.makeSausage();
    }
}
