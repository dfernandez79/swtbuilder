package swtbuilder;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

import java.util.Map;

public interface LayoutDescription {

    Layout createLayout();

    void layoutControl(ControlDescription<?, ?> description, Control control, Map<String, Control> refs);

}
