package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompositeDescription
        extends AbstractControlDescription<CompositeDescription, Composite>
        implements CompositeBuilder {

    private final List<ControlDescription> children = new ArrayList<>();
    private LayoutDescription layoutDescription = new FormLayoutDescription();

    public CompositeDescription() {
        this(null);
    }

    public CompositeDescription(String id) {
        super(id, Composite::new);
    }

    @Override
    public <T extends ControlDescription> T add(T description) {
        children.add(description);
        return description;
    }

    @Override
    protected void setUpControl(Composite control, Map<String, Control> controlMap) {
        control.setLayout(layoutDescription.createLayout());

        List<Control> controls = children.stream()
                .map(d -> d.createControl(control, controlMap))
                .collect(Collectors.toList());

        Iterator<ControlDescription> childrenIterator = children.iterator();

        for (Control childControl : controls) {
            layoutDescription.layoutControl(childrenIterator.next(), childControl, controlMap);
        }
    }

}
