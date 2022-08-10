package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer{
    private static final double INITIAL_UNITS_ENERGY = 40;

    public GlacierExplorer(String name) {
        super(name, INITIAL_UNITS_ENERGY);
    }
}
