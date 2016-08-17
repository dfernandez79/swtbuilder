package swtbuilder;

import org.eclipse.swt.widgets.Control;

import java.util.EventObject;

public interface NoArgsEventListenerLambda<E extends EventObject, C extends Control> extends EventListenerLambda<E, C> {

    @Override
    default void handleEvent(E evt, C control, ControlRefs refs) {
        handleEvent();
    }

    void handleEvent();

}
