package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{
    public static final double INITIAL_KG = 5.50;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KG, price);
    }

    @Override
    public void eat() {
        this.setKg(this.getKg() + 5.70);
    }
}
