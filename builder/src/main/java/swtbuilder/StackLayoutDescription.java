package swtbuilder;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Control;

public class StackLayoutDescription implements LayoutDescription {
    private final String topControlId;
    private final int marginWidth;
    private final int marginHeight;

    public StackLayoutDescription(String topControlId, int marginWidth, int marginHeight) {
        this.topControlId = topControlId;
        this.marginWidth = marginWidth;
        this.marginHeight = marginHeight;
    }

    @Override
    public StackLayout createLayout(ControlRefs refs) {
        StackLayout stackLayout = new StackLayout();
        stackLayout.marginHeight = marginHeight;
        stackLayout.marginWidth = marginWidth;
        if (topControlId != null) {
            stackLayout.topControl = refs.get(topControlId);
        }
        return stackLayout;
    }

    @Override
    public void layoutControl(Control control, LayoutDataDescription layoutDataDescription,
                              ControlRefs refs) {

    }
}
