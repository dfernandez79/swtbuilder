package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

public class TextDescription extends AbstractControlDescription<TextDescription, Text> {

    private String text;

    public TextDescription() {
        super(Text::new);
        style(SWT.BORDER);
    }

    @Override
    protected void setUpControl(Text control, ControlRefs refs) {
        if (text != null) {
            control.setText(text);
        }
    }

    public TextDescription text(String text) {
        this.text = text;
        return this;
    }

}
