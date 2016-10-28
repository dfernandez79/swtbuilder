package swtbuilder;

import org.eclipse.swt.widgets.Combo;

public class ComboDescription extends AbstractControlDescription<ComboDescription, Combo> {
    private int initialSelection = -1;
    private String[] items = {};

    public ComboDescription() {
        super(Combo::new);
    }

    @Override
    protected void setUpControl(Combo control, ControlRefs refs) {
        control.setItems(items);
        control.select(initialSelection);
    }

    public ComboDescription select(int index) {
        initialSelection = index;
        return this;
    }

    public ComboDescription items(String... items) {
        this.items = items;
        return this;
    }
}
