package swtmockups.model;

import org.eclipse.swt.widgets.Control;

public class ControlInstance {
    public final ControlDescription description;
    public final Control control;

    public ControlInstance(ControlDescription description, Control control) {
        this.description = description;
        this.control = control;
    }
}
