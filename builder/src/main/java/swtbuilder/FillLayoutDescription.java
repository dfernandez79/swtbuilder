package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;

public class FillLayoutDescription implements LayoutDescription {
    private final int type;
    private final int marginWidth;
    private final int marginHeight;
    private final int spacing;

    public FillLayoutDescription() {
        this(SWT.HORIZONTAL, 0, 0, 0);
    }

    public FillLayoutDescription(int type, int marginWidth, int marginHeight, int spacing) {
        this.type = type;
        this.marginWidth = marginWidth;
        this.marginHeight = marginHeight;
        this.spacing = spacing;
    }

    @Override
    public FillLayout createLayout() {
        FillLayout layout = new FillLayout(type);
        layout.marginWidth = marginWidth;
        layout.marginHeight = marginHeight;
        layout.spacing = spacing;
        return layout;
    }

    @Override
    public void layoutControl(Control control, LayoutDataDescription layoutDataDescription, ControlRefs refs) {
    }
}
