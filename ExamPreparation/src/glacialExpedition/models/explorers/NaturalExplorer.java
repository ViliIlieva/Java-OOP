package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double INITIAL_UNITS_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, INITIAL_UNITS_ENERGY);
    }

    @Override
    public void search() {
       if((getEnergy() - 7) < 0){
           setEnergy(0);
       }
       setEnergy(getEnergy() -7);
    }
}
