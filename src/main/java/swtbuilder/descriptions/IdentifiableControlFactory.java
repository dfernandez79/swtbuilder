package swtbuilder.descriptions;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import swtbuilder.Controls;

public interface IdentifiableControlFactory<C extends Control> {

    String id();

    C createControl(Composite parent);

    // TODO Declare exceptions
    C setUpControl(Controls controls, C control);

}
