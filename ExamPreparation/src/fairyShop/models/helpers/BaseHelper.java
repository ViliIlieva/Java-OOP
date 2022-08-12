package fairyShop.models.helpers;

import fairyShop.models.instruments.Instrument;

import java.util.ArrayList;
import java.util.Collection;

import static fairyShop.common.ExceptionMessages.*;

public abstract class BaseHelper implements Helper{
    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    public BaseHelper(String name, int energy) {
        this.setName(name);
        this.energy = energy;
        this.instruments = new ArrayList<>();
    }

    public void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void work() {
        setEnergy(getEnergy() - 10);
        if(getEnergy() - 10 < 0) {
            setEnergy(0);
        }
    }

    @Override
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return getEnergy() > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return instruments;
    }
    public long getBrokenInstruments (){
        return instruments.stream().filter(Instrument::isBroken).count();
    }
}
