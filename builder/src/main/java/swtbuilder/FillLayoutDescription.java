package swtbuilder;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;

public class FillLayoutDescription implements LayoutDescription {
    @Override
    public FillLayout createLayout() {
        return new FillLayout();
    }

    @Override
    public void layoutControl(Control control, LayoutDataDescription layoutDataDescription, ControlRefs refs) {}
}
