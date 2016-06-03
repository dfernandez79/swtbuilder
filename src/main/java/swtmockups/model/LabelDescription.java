package swtmockups.model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.Optional;

public class LabelDescription extends ControlDescription {
    private final String text;

    public LabelDescription(Optional<String> id, FormDataExpressions formDataExpressions, String text) {
        super(id, formDataExpressions);
        this.text = text;
    }

    @Override
    public ControlInstance instantiate(Composite parent, Object controller) {
        Label label = new Label(parent, SWT.NONE);
        label.setText(text);
        return new ControlInstance(this, label);
    }
}
