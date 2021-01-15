package bt3.complain.concrete.handler;

import bt3.complain.Complain;
import bt3.complain.ComplainHandler;

//Singleton - chỉ cần một thể hiện của manager
public class Manager extends ComplainHandler {

    private static Manager instance;

    private Manager(Complain complainCanHandle) {
        super(complainCanHandle);
    }

    public static Manager getInstance(Complain complainCanHandle) {
        if (instance == null) {
            synchronized (Manager.class) {
                if (instance == null) {
                    instance = new Manager(complainCanHandle);
                }
            }
        }

        return instance;
    }

    @Override
    protected void doHandling(String message) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Handling: " + message);
        System.out.println("Manager is talking with customer");
    }
}
