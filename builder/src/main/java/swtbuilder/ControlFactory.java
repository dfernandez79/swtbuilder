package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface ControlFactory<C extends Control> {
    default C createControl(Composite parent) {
        return createControl(parent, new ControlRefs());
    }

    C createControl(Composite parent, ControlRefs refs);
}
