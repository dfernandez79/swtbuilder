package swtbuilder.descriptions;

import org.eclipse.swt.widgets.Composite;

public interface LayoutDescription {

    LayoutDataDescription newLayoutDataDescription();

    void setLayoutOf(Composite parent);

}
