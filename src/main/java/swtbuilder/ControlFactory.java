package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.Map;

public interface ControlFactory<C extends Control> {

    default C createControl(Composite parent) {
        return createControl(parent, null);
    }

    C createControl(Composite parent, Map<String, Control> refs);

}
