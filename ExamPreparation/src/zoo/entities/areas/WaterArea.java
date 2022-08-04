package zoo.entities.areas;

public class WaterArea extends BaseArea{
    private static final int DEF_CAPACITY = 10;

    public WaterArea(String name) {
        super(name, DEF_CAPACITY);
    }
}
