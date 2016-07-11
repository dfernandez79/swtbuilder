package swtbuilder.descriptions;

import org.eclipse.swt.widgets.Control;
import swtbuilder.Controls;

public interface LayoutDataDescription extends PropertySetterChain<LayoutDataDescription> {

    void applyLayout(Controls controls, Control instance);

}
