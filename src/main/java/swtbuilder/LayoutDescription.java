package swtbuilder;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

import java.util.Map;

public interface LayoutDescription {

    Layout createLayout();

    void layoutControl(Control control, LayoutDataDescription layoutDataDescription, Map<String, Control> refs);

}
