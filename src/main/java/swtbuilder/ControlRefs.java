package swtbuilder;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import java.util.HashMap;
import java.util.Map;

public class ControlRefs {

    private final Map<String, Control> refs = new HashMap<>();

    public void add(String id, Control control) throws DuplicateIdException {
        if (id != null) {
            if (refs.containsKey(id)) {
                throw new DuplicateIdException();
            }
            refs.put(id, control);
        }
    }

    public Control get(String name) throws UnknownControlException {
        Control result = refs.get(name);
        if (result == null) {
            throw new UnknownControlException();
        }
        return result;
    }

    public Label label(String name) {
        return (Label) get(name);
    }

    public Combo combo(String name) {
        return (Combo) get(name);
    }

}
