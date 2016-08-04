package swtbuilder;

import org.eclipse.swt.widgets.Control;

import java.util.Map;
import java.util.function.Consumer;

public interface ControlDescription<D extends ControlDescription, C extends Control> extends ControlFactory<C> {

    D style(int style);

    @SuppressWarnings("unchecked")
    default D chain(Consumer<D> fn) {
        fn.accept((D) this);
        return (D) this;
    }

    @SuppressWarnings("unchecked")
    default D chain(Runnable block) {
        block.run();
        return (D) this;
    }

    D top(Object value);

    D left(Object value);

    D right(Object value);

    D bottom(Object value);

    Map<String, Object> layoutData();

}