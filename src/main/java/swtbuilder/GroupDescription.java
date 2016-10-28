package swtbuilder;

import org.eclipse.swt.widgets.Group;

public class GroupDescription extends AbstractCompositeDescription<GroupDescription, Group> {
    private String text;

    public GroupDescription() {
        super(Group::new);
    }

    @Override
    protected void setUpControl(Group control, ControlRefs refs) {
        if (text != null) {
            control.setText(text);
        }

        super.setUpControl(control, refs);
    }

    public GroupDescription text(String text) {
        this.text = text;
        return this;
    }
}
