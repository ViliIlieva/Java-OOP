package zoo.entities.areas;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseArea implements Area{
    private String name;
    private int capacity;
    private List<Food> foods;
    private List<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        foods = new ArrayList<>();
        animals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().equals("")){
            throw new NullPointerException(ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public int sumCalories() {
        return this.foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if(this.animals.size() == capacity){
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        String animalsOutput = animals.isEmpty()
                ? "none"
                : animals.stream().map(Animal::getName).collect(Collectors.joining(" "));
        return String.format("%s (%s):%n" +
                "Animals: %s%n" +
                "Foods: %d%n" +
                "Calories: %d",
                name, this.getClass().getSimpleName(),
                animalsOutput,
                foods.size(),
                sumCalories());
    }
}
