package fairyShop.repositories;

import fairyShop.models.helpers.BaseHelper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HelperRepository implements Repository<BaseHelper> {
    private Collection<BaseHelper> helpers;

    public HelperRepository() {
        this.helpers = new ArrayList<>();
    }

    @Override
    public Collection<BaseHelper> getModels() {
        return Collections.unmodifiableCollection(helpers);
    }

    @Override
    public void add(BaseHelper model) {
        helpers.add(model);
    }

    @Override
    public boolean remove(BaseHelper model) {
        return helpers.remove(model);
    }


    @Override
    public BaseHelper findByName(String name) {
        return helpers.stream().filter(h -> h.getName().equals(name)).findFirst().orElse(null);
    }
}
