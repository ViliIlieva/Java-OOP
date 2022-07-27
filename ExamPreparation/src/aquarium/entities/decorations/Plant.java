package aquarium.entities.decorations;

public class Plant extends BaseDecoration{
    private static final int INSTANT_COMFORT = 5;
    private static final double INSTANT_PRICE = 10;

    public Plant() {
        super(INSTANT_COMFORT, INSTANT_PRICE);
    }
}
