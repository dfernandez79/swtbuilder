package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ChildBuilder implements CompositeBuilder {

    private final List<LayoutAwareControlFactory<?>> children = new ArrayList<>();

    @Override
    public <T extends LayoutAwareControlFactory<?>> T add(T controlFactory) {
        children.add(controlFactory);
        return controlFactory;
    }

    public void createChildren(Composite parent, LayoutDescription layoutDescription, ControlRefs refs) {
        List<Control> controls = children.stream().map(d -> d.createControl(parent, refs)).collect(Collectors.toList());

        parent.setLayout(layoutDescription.createLayout());
        Iterator<LayoutAwareControlFactory<?>> childrenIterator = children.iterator();

        for (Control childControl : controls) {
            layoutDescription.layoutControl(childControl, childrenIterator.next(), refs);
        }
    }

}
