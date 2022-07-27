package aquarium.entities.decorations;

public class Ornament extends BaseDecoration{
    private static final int INSTANT_COMFORT = 1;
    private static final double INSTANT_PRICE = 5;


    public Ornament() {
        super(INSTANT_COMFORT, INSTANT_PRICE);
    }
}
