package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Control;

public class RowLayoutDescription implements LayoutDescription {
    private int type = SWT.HORIZONTAL;
    private boolean center = false;

    public RowLayoutDescription center(boolean value) {
        this.center = value;
        return this;
    }

    public RowLayoutDescription center() {
        return center(true);
    }

    public RowLayoutDescription vertical() {
        this.type = SWT.VERTICAL;
        return this;
    }

    public RowLayoutDescription horizontal() {
        this.type = SWT.HORIZONTAL;
        return this;
    }

    @Override
    public RowLayout createLayout(ControlRefs refs) {
        RowLayout layout = new RowLayout();
        layout.type = this.type;
        layout.center = this.center;
        return layout;
    }

    @Override
    public void layoutControl(Control control, LayoutDataDescription layoutDataDescription, ControlRefs refs) {
        int minWidth = SWT.DEFAULT;
        int minHeight = SWT.DEFAULT;

        Object layoutWidth = layoutDataDescription.layoutData("width");
        if (layoutWidth != null && layoutWidth instanceof Number) {
            minWidth = ((Number) layoutWidth).intValue();
        }

        Object layoutHeight = layoutDataDescription.layoutData("height");
        if (layoutHeight != null && layoutHeight instanceof Number) {
            minHeight = ((Number) layoutHeight).intValue();
        }

        if (layoutWidth != null || layoutHeight != null) {
            control.setLayoutData(new RowData(minWidth, minHeight));
        }
    }
}
