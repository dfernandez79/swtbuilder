package swtbuilder;

import org.eclipse.swt.widgets.Control;

public interface ControlDescription<D extends ControlDescription, C extends Control>
        extends Chainable<D>, LayoutAwareControlFactory<C> {

    D style(int style);

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