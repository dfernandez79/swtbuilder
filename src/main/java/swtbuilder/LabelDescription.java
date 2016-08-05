package swtbuilder;

import org.eclipse.swt.widgets.Label;

public class LabelDescription extends AbstractControlDescription<LabelDescription, Label> {

    private String text;

    public LabelDescription() {
        super(Label::new);
    }

    @Override
    protected void setUpControl(Label control, ControlRefs refs) {
        if (text != null) {
            control.setText(text);
        }
    }

    public LabelDescription text(String text) {
        this.text = text;
        return this;
    }
}
