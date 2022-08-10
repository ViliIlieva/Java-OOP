package glacialExpedition.models.states;

import java.util.Collection;
import java.util.List;

public interface State {
    Collection<String> getExhibits();

    String getName();
}
