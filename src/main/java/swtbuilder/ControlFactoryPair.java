package swtbuilder;

import org.eclipse.swt.widgets.Control;
import swtbuilder.descriptions.IdentifiableControlFactory;

public class ControlFactoryPair<C extends Control> {

    private final IdentifiableControlFactory<C> factory;
    private final C control;

    public ControlFactoryPair(IdentifiableControlFactory<C> factory, C control) {
        this.factory = factory;
        this.control = control;
    }

    public void setUpControl(Controls controls) {
        factory.setUpControl(controls, control);
    }

    public String id() {
        return factory.id();
    }

    public C control() {
        return control;
    }

}
