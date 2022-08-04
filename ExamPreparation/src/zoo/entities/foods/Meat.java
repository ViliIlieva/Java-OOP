package zoo.entities.foods;

public class Meat extends BaseFood{
    public static final int DEF_CALORIES = 70;
    public static final double DEF_PRICE = 10;

    public Meat() {
        super(DEF_CALORIES, DEF_PRICE);
    }
}