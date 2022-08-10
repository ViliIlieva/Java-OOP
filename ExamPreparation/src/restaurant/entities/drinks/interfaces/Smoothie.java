package restaurant.entities.drinks.interfaces;

public class Smoothie extends BaseBeverage{
    private static final double CONSTANT_PRICE = 4.50;

    public Smoothie(String name, int counter, String brand) {
        super(name, counter, CONSTANT_PRICE, brand);
    }
}
