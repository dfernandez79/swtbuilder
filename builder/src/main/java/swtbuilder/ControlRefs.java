package swtbuilder;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.*;

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

    public Button button(String name) {
        return (Button) get(name);
    }

    public Combo combo(String name) {
        return (Combo) get(name);
    }

    public Text textInput(String name) {
        return (Text) get(name);
    }

    public Group group(String name) {
        return (Group) get(name);
    }

    public Composite composite(String name) {
        return (Composite) get(name);
    }
}
