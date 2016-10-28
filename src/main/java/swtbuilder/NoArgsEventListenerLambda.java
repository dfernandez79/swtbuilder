package swtbuilder;

import java.util.EventObject;

import org.eclipse.swt.widgets.Control;

public interface NoArgsEventListenerLambda<E extends EventObject, C extends Control> extends EventListenerLambda<E, C> {
    @Override
    default void handleEvent(E evt, C control, ControlRefs refs) {
        handleEvent();
    }

    void handleEvent();
}
