package swtbuilder.descriptions;

import org.eclipse.swt.widgets.Control;

import java.util.function.Consumer;

public interface ControlDescription<C extends Control>
        extends IdentifiableControlFactory<C>, PropertySetterChain<ControlDescription<C>> {

    ControlDescription<C> style(int style);

    ControlDescription<C> chain(Consumer<ControlDescription<C>> fn);

    ControlDescription<C> top(Object value);

    ControlDescription<C> left(Object value);

    ControlDescription<C> right(int value);

    ControlDescription<C> bottom(int value);

}
