import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.graphics.impl.ViewImpl;
import it.unibo.jetpackjoyride.model.impl.Money;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;

public class JetpackJoyride {
    public static void main(String[] args) {
        View view =  new ViewImpl(Map.of(), Set.of(), new PlayerImpl(null, null, null), List.of(new Money(null, null, null)));
        view.renderGame();
    }
}
