package bt3.complain.concrete.handler;

import bt3.complain.Complain;
import bt3.complain.ComplainHandler;

//Singleton - chỉ cần một giám đốc
public class Director extends ComplainHandler {

    private static Director instance;

    private Director(Complain complainCanHandle) {
        super(complainCanHandle);
    }

    public static Director getInstance(Complain complainCanHandle) {
        if (instance == null) {
            synchronized (Director.class) {
                if (instance == null) {
                    instance = new Director(complainCanHandle);
                }
            }
        }

        return instance;
    }

    @Override
    protected void doHandling(String message) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Handling: " + message);
        System.out.println("Director is talking with customer");
    }
}
