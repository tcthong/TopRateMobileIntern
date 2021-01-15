package bt3;

import bt3.command.CommandFactory;
import bt3.command.CommandType;
import bt3.complain.Complain;
import bt3.complain.ComplainHandler;
import bt3.complain.concrete.handler.Chef;
import bt3.complain.concrete.handler.Director;
import bt3.complain.concrete.handler.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ClientTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Waitress waitress = new Waitress();
        List<Chef> chefs = getAllChefs();

        while (true) {

            displayMenu(scanner);

            System.out.println("--------------------------------------------------");
            System.out.print("Chọn số thứ tự các món ăn bạn muốn đặt (các số thứ tự cách nhau bằng dấu cách): ");
            String foods = scanner.nextLine();
            String[] orderStrings = foods.trim().split("\\s+");

            for (String orderStr : orderStrings) {
                int foodPosition = Integer.parseInt(orderStr);
                addCommandTo(chefs, waitress, foodPosition);
            }

            waitress.oderUp();

            ComplainHandler complainHandler = getComplainHandler(chefs);
            handleComplain(scanner, complainHandler);
        }
    }

    //Thêm món ăn khách hàng đã chọn vào order hiện tại của bồi bàn
    private static void addCommandTo(List<Chef> chefs, Waitress waitress, int foodPosition) {

        switch (foodPosition) {
            case CommandType.Position.COFFEE_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.COFFEE));
                break;

            case CommandType.Position.BREAD_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.BREAD));
                break;

            case CommandType.Position.CHIP_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.CHIP));
                break;

            case CommandType.Position.SALAD_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.SALAD));
                break;

            case CommandType.Position.SANDWICH_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.SANDWICH));
                break;

            case CommandType.Position.SAUSAGE_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.SAUSAGE));
                break;

            case CommandType.Position.CHICKEN_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.CHICKEN));
                break;

            case CommandType.Position.BISCUIT_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.BISCUIT));
                break;

            case CommandType.Position.PIZZA_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.PIZZA));
                break;

            case CommandType.Position.BURGER_POSITION:
                waitress.addCommand(CommandFactory.getCommand(chefs, CommandType.BURGER));
                break;
        }
    }


    //Hiển thị thực đơn cho khách hàng chọn món
    private static void displayMenu(Scanner scanner) {
        System.out.println("--------------------------------------------------");
        System.out.print("Xin chào. Bạn tên gì: ");
        String name = scanner.nextLine();
        System.out.println("--------------------------------------------------");
        System.out.println("Chào " + name + " bạn muốn ăn gì?");
        System.out.println("Menu");
        System.out.println(CommandType.Position.COFFEE_POSITION + ". Coffee");
        System.out.println(CommandType.Position.BREAD_POSITION + ". Bread");
        System.out.println(CommandType.Position.CHIP_POSITION + ". Chip");
        System.out.println(CommandType.Position.SALAD_POSITION + ". Salad");
        System.out.println(CommandType.Position.SANDWICH_POSITION + ". Sandwich");
        System.out.println(CommandType.Position.SAUSAGE_POSITION + ". Sausage");
        System.out.println(CommandType.Position.CHICKEN_POSITION + ". Chicken");
        System.out.println(CommandType.Position.BISCUIT_POSITION + ". Biscuit");
        System.out.println(CommandType.Position.PIZZA_POSITION + ". Pizza");
        System.out.println(CommandType.Position.BURGER_POSITION + ". Burger");
    }


    //Xử lý ý kiến của khách hàng
    private static void handleComplain(Scanner scanner, ComplainHandler complainHandler) {
        String orderAnswer;
        do {
            System.out.print("Bạn có ý kiến gì về bữa ăn không (Y/N): ");
            orderAnswer = scanner.nextLine();
        } while (!orderAnswer.equalsIgnoreCase("y") && !orderAnswer.equalsIgnoreCase("n"));

        if (orderAnswer.equalsIgnoreCase("Y")) {
            displayComplains();

            String msgHead = "Chọn mức độ quý khách muốn: ";
            String msgRetry = "Không hợp lệ. Vui lòng thử lại!";
            int minComplainLevel = 0;
            int maxComplainLevel = 3;
            int complainLevel = Utils.scanIntWithRetry(scanner, msgHead, msgRetry, minComplainLevel, maxComplainLevel);

            Complain complain = Complain.getComplainByLevel(complainLevel);

            System.out.print("Nhập ý kiến của quý khách: ");
            String message = scanner.nextLine();
            complainHandler.handleComplain(complain, message);
        }
    }

    //Hiển thị cho khách hàng menu các mức độ phản hồi ý kiến
    private static void displayComplains() {
        System.out.println("--------------------------------------------------");
        System.out.println("Mức độ ý kiến: ");
        System.out.println(Complain.GLAD.getLevel() + ": Glad");
        System.out.println(Complain.NORMAL.getLevel() + ": Normal");
        System.out.println(Complain.BAD.getLevel() + ": Bad");
        System.out.println(Complain.EXTREME_BAD.getLevel() + ": Extreme Bad");
    }

    //Trả về danh sách tất cả các đầu bếp của cửa hàng
    private static List<Chef> getAllChefs() {
        List<Chef> chefs = new ArrayList<>();
        chefs.add(new FirstChef("Chef1", Complain.NORMAL));
        chefs.add(new SecondChef("Chef2", Complain.NORMAL));

        return chefs;
    }


    //Trả về chain of reposibility cho việc xử lý ý kiến của khách hàng
    public static ComplainHandler getComplainHandler(List<Chef> chefs) {
        Random random = new Random();
        int randomChef = random.nextInt(chefs.size());

        ComplainHandler chef = chefs.get(randomChef);
        ComplainHandler manager = chef.setNext(Manager.getInstance(Complain.BAD));
        manager.setNext(Director.getInstance(Complain.EXTREME_BAD));

        return chef;
    }
}
