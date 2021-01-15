package bt3.complain;

public abstract class ComplainHandler {
    protected ComplainHandler nextComplainHandler;
    private final Complain complainCanHandle;

    protected ComplainHandler(Complain complain) {
        this.complainCanHandle = complain;
    }

    public ComplainHandler setNext(ComplainHandler nextComplainHandler) {
        this.nextComplainHandler = nextComplainHandler;
        return nextComplainHandler;
    }

    public void handleComplain(Complain complainHaveToHandle, String message) {
        //Đầu tiên người hiện tại xử lý ý kiến của khách hàng
        doHandling(message);

        //Nếu người hiện tại không xử lý được thì người cấp cao hơn sẽ xử lý
        if (!canHandle(complainHaveToHandle)) {
            System.out.println("Complain hasn't been handled yet. Forward the complain to next level");
            if (nextComplainHandler != null) {
                nextComplainHandler.handleComplain(complainHaveToHandle, message);
            } else {
                System.out.println("Complain can't handle");
            }
        } else {
            System.out.println("Complain has been handled");
        }
    }

    private boolean canHandle(Complain complainHaveToHandle) {
        return complainHaveToHandle.getLevel() <= complainCanHandle.getLevel();
    }

    protected abstract void doHandling(String message);
}
