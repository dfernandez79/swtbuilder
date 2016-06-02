package swtmockups.model;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.Optional;

public interface ControlDescription {
    Optional<String> id();

    Control createControl(Composite parent, Object controller);
}
