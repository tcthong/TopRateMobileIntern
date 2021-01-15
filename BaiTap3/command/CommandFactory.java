package bt3.command;

import bt3.DoableFood;
import bt3.command.details.*;
import bt3.complain.concrete.handler.Chef;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandFactory {
    private static final Random RANDOM = new Random();

    private CommandFactory() {
    }

    //Trả về Command theo loại
    public static ICommand getCommand(List<Chef> chefs, CommandType type) {
        switch (type) {
            case CHIP:
                DoableFood.IDoableChip chipChef = (DoableFood.IDoableChip) getRandomChefByFoodType(chefs, DoableFood.IDoableChip.class);
                return new ChipCommand(chipChef);

            case BREAD:
                DoableFood.IDoableBread breadChef = (DoableFood.IDoableBread) getRandomChefByFoodType(chefs, DoableFood.IDoableBread.class);
                return new BreadCommand(breadChef);

            case PIZZA:
                DoableFood.IDoablePizza pizzaChef = (DoableFood.IDoablePizza) getRandomChefByFoodType(chefs, DoableFood.IDoablePizza.class);
                return new PizzaCommand(pizzaChef);

            case SALAD:
                DoableFood.IDoableSalad saladChef = (DoableFood.IDoableSalad) getRandomChefByFoodType(chefs, DoableFood.IDoableSalad.class);
                return new SaladCommand(saladChef);

            case BURGER:
                DoableFood.IDoableBurger burgerChef = (DoableFood.IDoableBurger) getRandomChefByFoodType(chefs, DoableFood.IDoableBurger.class);
                return new BurgerCommand(burgerChef);

            case COFFEE:
                DoableFood.IDoableCoffee coffeeChef = (DoableFood.IDoableCoffee) getRandomChefByFoodType(chefs, DoableFood.IDoableCoffee.class);
                return new CoffeeCommand(coffeeChef);

            case BISCUIT:
                DoableFood.IDoableBiscuit biscuitChef = (DoableFood.IDoableBiscuit) getRandomChefByFoodType(chefs, DoableFood.IDoableBiscuit.class);
                return new BiscuitCommand(biscuitChef);

            case CHICKEN:
                DoableFood.IDoableChicken chickenChef = (DoableFood.IDoableChicken) getRandomChefByFoodType(chefs, DoableFood.IDoableChicken.class);
                return new ChickenCommand(chickenChef);

            case SAUSAGE:
                DoableFood.IDoableSausage sausageChef = (DoableFood.IDoableSausage) getRandomChefByFoodType(chefs, DoableFood.IDoableSausage.class);
                return new SausageCommand(sausageChef);

            case SANDWICH:
                DoableFood.IDoableSandwich sandwich = (DoableFood.IDoableSandwich) getRandomChefByFoodType(chefs, DoableFood.IDoableSandwich.class);
                return new SandwichCommand(sandwich);

            default:
                throw new IllegalArgumentException("Cửa hàng không có món ăn này");
        }
    }

    //Trả về ngẫu nhiên một chef trong danh sách chefs mà lớp của nó implement kiểu T
    //Ví dụ: T là IDoableBread thì sẽ trả về ngẫu nhiên một đầu bếp trong các đầu bếp của cửa hàng mà có thể làm được bánh mì
    private static Chef getRandomChefByFoodType(List<Chef> chefs, Class<?> T) {
        List<Chef> tempChefs = new ArrayList<>();
        for (Chef chef : chefs) {
            if (T.isAssignableFrom(chef.getClass())) {
                tempChefs.add(chef);
            }
        }

        int chipChefCount = tempChefs.size();
        int randomChef = RANDOM.nextInt(chipChefCount);
        return tempChefs.get(randomChef);
    }
}
