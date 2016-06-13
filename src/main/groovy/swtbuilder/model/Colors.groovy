package swtbuilder.model

import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.widgets.Display

class Colors {
    static Color darkGray() { systemColor(SWT.COLOR_DARK_GRAY) }

    static Color systemColor(int id) {
        Display.default.getSystemColor(id)
    }
}
