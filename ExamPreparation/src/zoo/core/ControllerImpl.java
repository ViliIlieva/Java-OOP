package zoo.core;

import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.*;
import static zoo.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private Map<String, Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new LinkedHashMap<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area;
        if (areaType.equals("WaterArea")) {
            area = new WaterArea(areaName);
        } else if (areaType.equals("LandArea")) {
            area = new LandArea(areaName);
        } else {
            throw new NullPointerException(INVALID_AREA_TYPE);
        }
        areas.put(area.getName(), area);
        return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;
        if (foodType.equals("Vegetable")) {
            food = new Vegetable();
        } else if (foodType.equals("Meat")) {
            food = new Meat();
        } else {
            throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }
        foodRepository.add(food);
        return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food food = foodRepository.findByType(foodType);
        if (food == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }
        foodRepository.remove(food);
        areas.get(areaName).addFood(food);
        return String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal;
        String areaType = "";
        if (animalType.equals("AquaticAnimal")) {
            animal = new AquaticAnimal(animalName, kind, price);
            areaType = "LandArea";
        } else if (animalType.equals("TerrestrialAnimal")) {
            animal = new TerrestrialAnimal(animalName, kind, price);
            areaType = "WaterArea";
        } else {
            throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }
        try {
            if(areaType.equals("")){
                throw new IllegalArgumentException(AREA_NOT_SUITABLE);
            }
            Area area = areas.get(areaName);
            area.addAnimal(animal);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = areas.get(areaName);
        area.feed();
        return String.format(ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = areas.get(areaName);
        double calculateKg = area.getAnimals().stream().mapToDouble(Animal::getKg).sum();
        return String.format(KILOGRAMS_AREA, areaName, calculateKg);
    }

    @Override
    public String getStatistics() {
        return areas.values().stream().map(Area::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
