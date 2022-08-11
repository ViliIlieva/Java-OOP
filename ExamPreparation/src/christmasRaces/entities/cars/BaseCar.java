package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.*;

public abstract class BaseCar implements Car {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    public void setModel(String model) {
        if (model == null || model.trim().equals("") || model.length() < 4) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL, model));
        }
        this.model = model;
    }

    public void setHorsePower(int horsePower) {
        if (("MuscleCar".equals(getClass().getSimpleName()) && (horsePower < 400 || horsePower > 600)) ||
                ("SportsCar".equals(getClass().getSimpleName()) && (horsePower < 250 || horsePower > 450))) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        this.horsePower = horsePower;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return cubicCentimeters / horsePower * laps;
    }
}
