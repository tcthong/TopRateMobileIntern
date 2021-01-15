package bt3;

import bt3.complain.Complain;
import bt3.complain.concrete.handler.Chef;

public class SecondChef
    extends Chef
    implements DoableFood.IDoableSausage, DoableFood.IDoableSandwich,
    DoableFood.IDoableSalad, DoableFood.IDoablePizza,
    DoableFood.IDoableChip, DoableFood.IDoableChicken,
    DoableFood.IDoableBiscuit {

    public SecondChef(String name, Complain complainCanHandle) {
        super(name, complainCanHandle);
    }

    @Override
    public void makeChip() {
        Utils.makeFood(getName(), "Chip");
    }

    @Override
    public void makePizza() {
        Utils.makeFood(getName(), "Pizza");
    }

    @Override
    public void makeSalad() {
        Utils.makeFood(getName(), "Salad");
    }

    @Override
    public void makeSandwich() {
        Utils.makeFood(getName(), "Sandwich");
    }

    @Override
    public void makeSausage() {
        Utils.makeFood(getName(), "Sausage");
    }

    @Override
    public void makeBiscuit() {
        Utils.makeFood(getName(), "Biscuit");
    }

    @Override
    public void makeChicken() {
        Utils.makeFood(getName(), "Chicken");
    }
}
