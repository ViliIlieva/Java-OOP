package fairyShop.core;

import fairyShop.models.helpers.BaseHelper;
import fairyShop.models.helpers.Happy;
import fairyShop.models.helpers.Helper;
import fairyShop.models.helpers.Sleepy;
import fairyShop.models.instruments.Instrument;
import fairyShop.models.instruments.InstrumentImpl;
import fairyShop.models.presents.Present;
import fairyShop.models.presents.PresentImpl;
import fairyShop.models.shop.ShopImpl;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        BaseHelper newHelper;
        if("Happy".equals(type)){
            newHelper = new Happy(helperName);
        }else if("Sleepy".equals(type)){
            newHelper = new Sleepy(helperName);
        }else{
            throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        helperRepository.add(newHelper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument newInstrument = new InstrumentImpl(power);
        Helper helper = helperRepository.findByName(helperName);
        if(helper == null){
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        helper.addInstrument(newInstrument);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present newPresent = new PresentImpl(presentName, energyRequired);
        presentRepository.add(newPresent);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        BaseHelper helperSuitable = helperRepository.getModels().stream()
                .filter(h -> h.getEnergy() > 50).findFirst().orElse(null);
        if(helperSuitable == null){
            throw new IllegalArgumentException(NO_HELPER_READY);
        }
        Present present = presentRepository.findByName(presentName);
        ShopImpl shop = new ShopImpl();
        shop.craft(present, helperSuitable);
        String presentIsDone = present.isDone() ? "done" : "not done";
        long countBrokenInstruments = helperSuitable.getBrokenInstruments();
        return String.format(PRESENT_DONE + COUNT_BROKEN_INSTRUMENTS, presentName, presentIsDone
                ,countBrokenInstruments);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!",presentRepository.getModels().stream()
                .filter(Present::isDone).count())).append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());

        this.helperRepository.getModels().stream().forEach( h ->{
            sb.append("Name: ").append(h.getName()).append(System.lineSeparator());
            sb.append("Energy: " + h.getEnergy()).append(System.lineSeparator());
            sb.append(String.format("Instruments: %d not broken left",h.getInstruments().stream().filter(i -> !i.isBroken()).count()))
                    .append(System.lineSeparator());
                });
        return sb.toString().trim();
    }
}
