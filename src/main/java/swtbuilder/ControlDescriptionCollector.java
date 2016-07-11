package swtbuilder;

import org.eclipse.swt.graphics.Point;
import swtbuilder.descriptions.ButtonDescription;
import swtbuilder.descriptions.ControlDescription;
import swtbuilder.descriptions.LabelDescription;
import swtbuilder.descriptions.LayoutDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControlDescriptionCollector {

    private final LayoutDescription layout;
    private final List<ControlDescription> children = new ArrayList<>();
    private Integer width = null;
    private Integer height = null;

    public ControlDescriptionCollector(LayoutDescription layout) {
        this.layout = layout;
    }

    public ButtonDescription button() {
        return button(null);
    }

    public ButtonDescription button(String id) {
        return addDescription(new ButtonDescription(id, layout.newLayoutDataDescription()));
    }

    public LabelDescription label() {
        return label(null);
    }

    public LabelDescription label(String id) {
        return addDescription(new LabelDescription(id, layout.newLayoutDataDescription()));
    }

    private <D extends ControlDescription> D addDescription(D description) {
        children.add(description);
        return description;
    }

    public void size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    List<ControlDescription> collectedChildren() {
        return children;
    }

    Optional<Point> optionalSize() {
        if (width == null || height == null) {
            return Optional.empty();
        }

        return Optional.of(new Point(width, height));
    }

}
