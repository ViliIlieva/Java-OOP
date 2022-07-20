package barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import barracksWars.models.units.*;
import jdk.jshell.spi.ExecutionControl;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
        switch (unitType) {
            case "Archer":
                return new Archer();
            case "Pikeman":
                return new Pikeman();
            case "Swordsman":
                return new Swordsman();
            case "Gunner":
                return new Gunner();
            case "Horseman":
                return new Horseman();
        }
        return null;
    }
}
