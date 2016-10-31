package swtbuilder;

import java.util.EventObject;
import java.util.function.BiConsumer;

import org.eclipse.swt.widgets.Control;

public interface BiConsumerEventListenerLambda<E extends EventObject, C extends Control>
    extends EventListenerLambda<E, C>, BiConsumer<E, C> {

    @Override
    default void handleEvent(E evt, C control, ControlRefs refs) {
        accept(evt, control);
    }
}
