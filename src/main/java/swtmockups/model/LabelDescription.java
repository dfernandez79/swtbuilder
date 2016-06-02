package swtmockups.model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import java.util.Optional;

public class LabelDescription implements ControlDescription {
    private final Optional<String> id;
    private final Optional<String> text;
    private final Optional<FormData> formData;

    public LabelDescription(Optional<String> id, Optional<String> text, Optional<FormData> formData) {
        this.id = id;
        this.text = text;
        this.formData = formData;
    }

    @Override
    public Optional<String> id() {
        return id;
    }

    @Override
    public Control createControl(Composite parent, Object controller) {
        Label label = new Label(parent, SWT.NONE);
        text.ifPresent(label::setText);
        formData.ifPresent(label::setLayoutData);
        return label;
    }
}
