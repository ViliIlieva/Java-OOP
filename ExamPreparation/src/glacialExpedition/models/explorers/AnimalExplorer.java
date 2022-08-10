package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer{
    private static final double INITIAL_UNITS_ENERGY = 100;

    public AnimalExplorer(String name) {
        super(name, INITIAL_UNITS_ENERGY);
    }

}
