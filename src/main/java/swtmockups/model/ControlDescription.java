package swtmockups.model;

import org.eclipse.swt.widgets.Composite;

import java.util.Optional;

public abstract class ControlDescription {
    public final Optional<String> id;
    public final FormDataExpressions formDataExpressions;

    protected ControlDescription(Optional<String> id, FormDataExpressions formDataExpressions) {
        this.id = id;
        this.formDataExpressions = formDataExpressions;
    }

    public abstract ControlInstance instantiate(Composite parent, Object controller);
}
