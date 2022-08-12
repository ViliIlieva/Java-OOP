package fairyShop.models.presents;

import static fairyShop.common.ExceptionMessages.*;

public class PresentImpl implements Present{
    private String name;
    private int energyRequired;

    public PresentImpl(String name, int energyRequired) {
        this.setName(name);
        this.setEnergyRequired(energyRequired);
    }

    public void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergyRequired(int energyRequired) {
        if(energyRequired < 0){
            throw new IllegalArgumentException(PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.energyRequired = energyRequired;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getEnergyRequired() {
        return energyRequired;
    }

    @Override
    public boolean isDone() {
        return energyRequired <= 0;
    }

    @Override
    public void getCrafted() {
        energyRequired -= 10;
        if(energyRequired - 10 < 0){
            energyRequired = 0;
        }
    }
}
