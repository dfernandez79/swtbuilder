package swtmockups.model;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Mockup {
    private final List<ControlDescription> controls = new ArrayList<>();
    private final Optional<Integer> width;
    private final Optional<Integer> height;

    public Mockup(Optional<Integer> width, Optional<Integer> height) {
        this.width = width;
        this.height = height;
    }

    public MockupInstance instantiate(Composite composite) {
        return instantiate(composite, ControllerFactory.NULL);
    }

    public MockupInstance instantiate(Composite composite, ControllerFactory controllerFactory) {
        composite.setLayout(new FormLayout());
        width.ifPresent(w -> height.ifPresent(h -> composite.setSize(new Point(w, h))));

        Object controller = controllerFactory.create();
        for (ControlDescription controlDescription : controls) {
            controlDescription.createControl(composite, controller);
        }

        return new MockupInstance();
    }

    public void add(ControlDescription control) {
        controls.add(control);
    }
}
