package zoo.entities.foods;

public class Vegetable extends BaseFood{
    public static final int DEF_CALORIES = 50;
    public static final double DEF_PRICE = 5;

    public Vegetable() {
        super(DEF_CALORIES, DEF_PRICE);
    }
}
