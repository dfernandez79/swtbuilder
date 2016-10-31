package swtbuilder;

import java.util.EventObject;

import org.eclipse.swt.widgets.Control;

public interface EventListenerLambda<E extends EventObject, C extends Control> {
    void handleEvent(E evt, C control, ControlRefs refs);
}
