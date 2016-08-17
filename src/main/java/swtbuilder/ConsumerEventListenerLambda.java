package swtbuilder;

import org.eclipse.swt.widgets.Control;

import java.util.EventObject;
import java.util.function.Consumer;

public interface ConsumerEventListenerLambda<E extends EventObject, C extends Control>
        extends EventListenerLambda<E, C>, Consumer<E> {

    @Override
    default void handleEvent(E evt, C control, ControlRefs refs) {
        accept(evt);
    }

}
