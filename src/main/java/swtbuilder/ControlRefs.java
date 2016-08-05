package swtbuilder;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import java.util.HashMap;
import java.util.Map;

public class ControlRefs {

    private final Map<String, Control> refs = new HashMap<>();

    public void add(String id, Control control) {
        if (id != null) {
            refs.put(id, control);
        }
    }

    public Control get(String name) {
        return refs.get(name);
    }

    public Label label(String name) {
        return (Label) get(name);
    }

    public Combo combo(String name) {
        return (Combo) get(name);
    }

}
