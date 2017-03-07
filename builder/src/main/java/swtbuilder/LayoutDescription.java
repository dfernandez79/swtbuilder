package swtbuilder;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public interface LayoutDescription {
    Layout createLayout(ControlRefs refs);

    void layoutControl(Control control, LayoutDataDescription layoutDataDescription, ControlRefs refs);
}
