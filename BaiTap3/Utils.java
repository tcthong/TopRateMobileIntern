package bt3;

import bt3.complain.Complain;

import java.util.Random;
import java.util.Scanner;

public class Utils {
    private static final Random random = new Random();

    public static final int RANDOM_BOUND = 15;
    public static final int RANDOM_NUMBER = 2;

    public static void makeFood(String chefName, String foodName) {
        if(Utils.RANDOM_NUMBER != random.nextInt(Utils.RANDOM_BOUND)) {
            System.out.println(chefName + ": Make " + foodName + " Done");
        } else {
            System.out.println(chefName + ": Make " +  foodName + " Fail");
        }
    }

    //Nhận đầu vào từ bàn phím và chuyển chuỗi người dùng nhập vào sang số nguyên.
    //Nếu số đó không nằm trong khoảng [min, max] hoặc người dùng không nhập số thì cho nhập lại.
    //messageHead là chuỗi hiển thị để yêu cầu người dùng nhập 1 số.
    //messageRetry là chuỗi hiển thị khi người dùng nhập lỗi và yêu cầu họ nhập lại
    public static int scanIntWithRetry(Scanner scanner, String messageHead, String messageRetry, int min, int max) {
        String intStr;

        while (true) {
            try {
                System.out.print(messageHead);
                intStr = scanner.nextLine();
                int result = Integer.parseInt(intStr);
                if (result > max && result < min) {
                    continue;
                }

                return result;
            } catch (NumberFormatException e) {
                System.out.print(messageRetry);
            }
        }
    }
}
