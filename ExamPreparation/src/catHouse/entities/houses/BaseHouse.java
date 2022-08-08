package catHouse.entities.houses;

import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public abstract class BaseHouse implements House{
    private String name;
    private int	capacity;
    private List<Toy> toys;
    private List<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        toys = new ArrayList<>();
        cats = new ArrayList<>();
    }

    @Override
    public int sumSoftness() {
        return this.toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
        if(cats.size() == capacity){
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public void feeding() {
        this.cats.forEach(Cat::eating);
    }

    @Override
    public String getStatistics() {
        String catsOutput = cats.isEmpty() ? "none"
                : cats.stream().map(Cat::getName).collect(Collectors.joining(" "));
        return String.format("%s %s%n" +
                        "Cats: %s%n" +
                        "Toys: %d Softness: %d",
                name, this.getClass().getSimpleName(),
                catsOutput,
                toys.size(), sumSoftness());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public List<Cat> getCats() {
        return cats;
    }

    @Override
    public List<Toy> getToys() {
        return toys;
    }

}
