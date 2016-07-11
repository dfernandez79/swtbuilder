package swtbuilder;

import org.eclipse.swt.widgets.Control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controls {

    private final Map<Object, Control> controlMap;
    private final List<ControlFactoryPair<? extends Control>> pairs;

    public Controls(List<ControlFactoryPair<? extends Control>> pairs) {
        this.pairs = pairs;
        this.controlMap = new HashMap<>(pairs.size());

        int i = 0;
        for (ControlFactoryPair pair : pairs) {
            controlMap.put(i, pair.control());
            if (pair.id() != null) {
                controlMap.put(pair.id(), pair.control());
            }
            i++;
        }
    }

    public Control get(Object key) {
        return controlMap.get(key);
    }

    public Iterable<ControlFactoryPair<? extends Control>> descriptionInstancePairs() {
        return pairs;
    }

}
