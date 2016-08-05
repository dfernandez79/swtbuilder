package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChildBuilder implements CompositeBuilder {

    private final List<ControlDescription> children = new ArrayList<>();

    @Override
    public <T extends ControlDescription> T add(T description) {
        children.add(description);
        return description;
    }

    public void createChildren(Composite parent, LayoutDescription layoutDescription, Map<String, Control> refs) {
        List<Control> controls = children.stream()
                .map(d -> d.createControl(parent, refs))
                .collect(Collectors.toList());

        parent.setLayout(layoutDescription.createLayout());
        Iterator<ControlDescription> childrenIterator = children.iterator();

        for (Control childControl : controls) {
            layoutDescription.layoutControl(childrenIterator.next(), childControl, refs);
        }
    }

}
