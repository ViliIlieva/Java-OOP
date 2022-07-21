package spaceStation.models.astronauts;

import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

public class BaseAstronaut implements Astronaut{
    private String name;
    private double oxygen;
    private Bag bag;


    public BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    public void setOxygen(double oxygen) {
        if(oxygen < 0){
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public boolean canBreath() {
        return this.oxygen > 0;
    }

    @Override
    public Bag getBag() {
        return bag;
    }


    @Override
    public void breath() {
        oxygen = oxygen - 10;
    }
}
