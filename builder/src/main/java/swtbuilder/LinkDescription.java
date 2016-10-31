package swtbuilder;

import org.eclipse.swt.widgets.Link;

public class LinkDescription extends AbstractControlDescription<LinkDescription, Link> {
    private String text;

    public LinkDescription() {
        super(Link::new);
    }

    @Override
    protected void setUpControl(Link control, ControlRefs refs) {
        if (text != null) {
            control.setText(text);
        }
    }

    public LinkDescription text(String text) {
        this.text = text;
        return this;
    }
}
