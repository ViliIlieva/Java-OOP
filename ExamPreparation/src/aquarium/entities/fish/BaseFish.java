package aquarium.entities.fish;

import aquarium.common.ExceptionMessages;

public abstract class BaseFish implements Fish{
    private String name;
    private String species;
    private int size;
    private double price;

    public BaseFish(String name, String species, double price) {
        this.setName(name);
        this.setSpecies(species);
        this.setPrice(price);
    }

    @Override
    public void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.FISH_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setSpecies(String species) {
        if(species == null || species.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.SPECIES_NAME_NULL_OR_EMPTY);
        }
        this.species = species;
    }

    private void setPrice(double price) {
        if(price <= 0){
            throw new IllegalArgumentException(ExceptionMessages.FISH_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;
    }

    @Override
    public void eat() {
        this.size += 5;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

