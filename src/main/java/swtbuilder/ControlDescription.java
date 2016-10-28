package swtbuilder;

import org.eclipse.swt.widgets.Control;

import java.util.function.Consumer;

public interface ControlDescription<D extends ControlDescription<D, C>, C extends Control>
    extends Chainable<D>, LayoutAwareControlFactory<C> {

    D style(int style);

    D setUp(Consumer<C> fn);

    D width(int width);

    D height(int height);

    D layoutData(String name, Object value);

    D background(int systemColor);

    default D size(int width, int height) {
        return width(width).height(height);
    }

    default D top(Object value) {
        return chain(() -> layoutData("top", value));
    }

    default D left(Object value) {
        return chain(() -> layoutData("left", value));
    }

    default D right(Object value) {
        return chain(() -> layoutData("right", value));
    }

    default D bottom(Object value) {
        return chain(() -> layoutData("bottom", value));
    }
}
