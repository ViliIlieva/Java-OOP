package glacialExpedition.models.explorers;

import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public abstract class BaseExplorer implements Explorer{
    private String name;
    private double energy;
    private Suitcase suitcase;

    public BaseExplorer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.suitcase = new Carton();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        if(this.energy < 0){
            throw new IllegalArgumentException(EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public boolean canSearch() {
        return energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return suitcase;
    }

    @Override
    public void search() {
        if(this.energy - 15 < 0){
            this.energy = 0;
        }
        this.energy -= 15;
    }
    @Override
    public String toString(){
        return String.format(FINAL_EXPLORER_NAME, name) + System.lineSeparator() +
                String.format(FINAL_EXPLORER_ENERGY, energy) + System.lineSeparator() +
                String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS,
                        (suitcase.getExhibits().size() == 0 ?
                                "None" :
                                String.join(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, suitcase.getExhibits())));
    }
}
