package swtbuilder;

import org.eclipse.swt.widgets.Control;

public interface LayoutAwareControlFactory<C extends Control> extends ControlFactory<C>, LayoutDataDescription {
}
