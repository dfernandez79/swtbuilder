package swtbuilder.descriptions;

import org.eclipse.swt.widgets.Label;

public class LabelDescription extends GenericControlDescription<Label> {

    public LabelDescription(String id,
                            LayoutDataDescription layoutDataDescription) {
        super(id, layoutDataDescription, Label::new);
    }

}
