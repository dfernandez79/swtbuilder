package swtbuilder;

import org.eclipse.swt.widgets.List;

import java.util.ArrayList;

public class ListDescription extends AbstractControlDescription<ListDescription, List> {
    private final java.util.List<String> items = new ArrayList<>();

    public ListDescription() {
        super(List::new);
    }

    @Override
    protected void setUpControl(List control, ControlRefs refs) {
        control.setItems(items.toArray(new String[items.size()]));
    }

    public ListDescription item(String text) {
        items.add(text);
        return this;
    }
}
