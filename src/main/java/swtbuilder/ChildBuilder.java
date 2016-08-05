package swtbuilder;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChildBuilder implements CompositeBuilder {

    private final List<LayoutAwareControlFactory> children = new ArrayList<>();
    private LayoutDescription layoutDescription = new FormLayoutDescription();
    private Integer width = null;
    private Integer height = null;

    @Override
    public <T extends LayoutAwareControlFactory> T add(T controlFactory) {
        children.add(controlFactory);
        return controlFactory;
    }

    @Override
    public void size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private Optional<Point> optionalSize() {
        return (width == null || height == null) ? Optional.empty() : Optional.of(new Point(width, height));
    }

    public void createChildren(Composite parent, ControlRefs refs) {
        List<Control> controls = children.stream().map(d -> d.createControl(parent, refs)).collect(Collectors.toList());

        optionalSize().ifPresent(parent::setSize);
        parent.setLayout(layoutDescription.createLayout());
        Iterator<LayoutAwareControlFactory> childrenIterator = children.iterator();

        for (Control childControl : controls) {
            layoutDescription.layoutControl(childControl, childrenIterator.next(), refs);
        }
    }

}
