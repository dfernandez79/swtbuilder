package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

public class TextDescription extends AbstractControlDescription<TextDescription, Text> {
    private String text;
    private String message;

    public TextDescription() {
        super(Text::new);
        style(SWT.BORDER);
    }

    @Override
    protected void setUpControl(Text control, ControlRefs refs) {
        if (text != null) {
            control.setText(text);
        }
        if (message != null) {
            control.setMessage(message);
        }
    }

    public TextDescription text(String text) {
        this.text = text;
        return this;
    }

    public TextDescription message(String message) {
        this.message = message;
        return this;
    }
}
