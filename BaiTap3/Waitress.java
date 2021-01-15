package bt3;

import bt3.command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Waitress {

    private List<ICommand> commands;

    public Waitress() {
        commands = new ArrayList<>();
    }

    public Waitress(List<ICommand> commands) {
        this.commands = commands;
    }

    public void addCommand(ICommand command) {
        commands.add(command);
    }

    public void oderUp() {
        for (ICommand command : commands) {
            command.execute();
        }

        //Khi bồi bàn đã chuyển order hiện tại thì xóa các command đi để chuẩn bị cho order tiếp theo
        commands.clear();
    }
}
