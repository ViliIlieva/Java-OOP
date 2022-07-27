package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static aquarium.common.ConstantMessages.WATER_NOT_SUITABLE;
import static aquarium.common.ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private List<Decoration> decorations;
    private List<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() == capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

        String fishWaterType = fish.getClass().getSimpleName().replaceAll("Fish", "");

        if(!this.getClass().getSimpleName().contains(fishWaterType)){
            throw  new IllegalStateException(WATER_NOT_SUITABLE);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        String fishOutput = fish.isEmpty()
                ? "none"
                : fish.stream().map(Fish::getName).collect(Collectors.joining(", "));
        return String.format("%s (%s):%n" +
                        "Fish: %s%n" +
                        "Decorations: %d%n" +
                        "Comfort: %d",
                name, this.getClass().getSimpleName(),
                fishOutput,
                decorations.size(),
                calculateComfort());
    }

    @Override
    public List<Decoration> getDecorations() {
        return decorations;
    }

    @Override
    public List<Fish> getFish() {
        return fish;
    }
}
