package bt3.complain.concrete.handler;

import bt3.complain.Complain;
import bt3.complain.ComplainHandler;

public class Chef extends ComplainHandler {

    private final String name;

    public Chef(String name, Complain complainCanHandle) {
        super(complainCanHandle);
        this.name = name;
    }

    @Override
    protected void doHandling(String message) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Handling: " + message);
        System.out.println(name + " chef is talking with customer");
    }

    public String getName() {
        return name;
    }
}
