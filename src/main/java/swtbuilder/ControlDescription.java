package swtbuilder;

import org.eclipse.swt.widgets.Control;

import java.util.Map;

public interface ControlDescription<D extends ControlDescription, C extends Control>
        extends Chainable<D>, ControlFactory<C> {

    D style(int style);

    D top(Object value);

    D left(Object value);

    D right(Object value);

    D bottom(Object value);

    Map<String, Object> layoutData();

}