package swtbuilder;

import java.util.EventObject;
import java.util.function.Consumer;

import org.eclipse.swt.widgets.Control;

public interface ConsumerEventListenerLambda<E extends EventObject, C extends Control>
    extends EventListenerLambda<E, C>, Consumer<E> {

    @Override
    default void handleEvent(E evt, C control, ControlRefs refs) {
        accept(evt);
    }
}
