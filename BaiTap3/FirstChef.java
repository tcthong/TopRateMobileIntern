package bt3;

import bt3.complain.Complain;
import bt3.complain.concrete.handler.Chef;

public class FirstChef
    extends Chef
    implements DoableFood.IDoableBread, DoableFood.IDoableCoffee,
    DoableFood.IDoableBurger, DoableFood.IDoableChicken,
    DoableFood.IDoableChip, DoableFood.IDoablePizza {
    public FirstChef(String name, Complain complainCanHandle) {
        super(name, complainCanHandle);
    }

    @Override
    public void makeCoffee() {
        Utils.makeFood(getName(), "Coffee");
    }

    @Override
    public void makeBread() {
        Utils.makeFood(getName(), "Bread");
    }

    @Override
    public void makeBurger() {
        Utils.makeFood(getName(), "Burger");
    }

    @Override
    public void makeChicken() {
        Utils.makeFood(getName(), "Chicken");
    }

    @Override
    public void makeChip() {
        Utils.makeFood(getName(), "Chip");
    }

    @Override
    public void makePizza() {
        Utils.makeFood(getName(), "Pizza");
    }
}
