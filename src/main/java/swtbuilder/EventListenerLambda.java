package swtbuilder;

import org.eclipse.swt.widgets.Control;

import java.util.EventObject;

public interface EventListenerLambda<E extends EventObject, C extends Control> {

    void handleEvent(E evt, C control, ControlRefs refs);

}
