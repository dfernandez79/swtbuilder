package swtmockups.model;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

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
        if (width.isPresent() && height.isPresent()) {
            composite.setSize(new Point(width.get(), height.get()));
        }

        Object controller = controllerFactory.create();

        List<ControlInstance> instances = controls
                .stream()
                .map(description -> description.instantiate(composite, controller))
                .collect(toList());

        Map<String, Control> namedControls = instances
                .stream()
                .filter(ci -> ci.description.id.isPresent())
                .collect(toMap(ci -> ci.description.id.get(), ci -> ci.control));

        instances.forEach(ci -> ci.control.setLayoutData(ci.description.formDataExpressions.createFormData(namedControls)));

        return new MockupInstance();
    }

    public void add(ControlDescription control) {
        controls.add(control);
    }
}
