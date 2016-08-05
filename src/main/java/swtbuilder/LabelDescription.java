package swtbuilder;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import java.util.Map;

public class LabelDescription extends AbstractControlDescription<LabelDescription, Label> {

    public LabelDescription() {
        super(Label::new);
    }

    @Override
    protected void setUpControl(Label control, Map<String, Control> controlMap) {

    }

}
