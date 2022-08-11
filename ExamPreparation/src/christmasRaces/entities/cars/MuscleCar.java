package christmasRaces.entities.cars;

public class MuscleCar extends BaseCar{
    private static final double CUBIC_CENTIMETERS = 5000;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }
}
