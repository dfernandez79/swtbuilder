package swtbuilder;

import org.eclipse.swt.widgets.Control;

public interface EventListenerLambda<E, C extends Control> {
    void handleEvent(E evt, C control, ControlRefs refs);
}
