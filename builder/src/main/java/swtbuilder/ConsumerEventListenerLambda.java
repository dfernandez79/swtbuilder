package swtbuilder;

import java.util.function.Consumer;

import org.eclipse.swt.widgets.Control;

public interface ConsumerEventListenerLambda<E, C extends Control>
    extends EventListenerLambda<E, C>, Consumer<E> {

    @Override
    default void handleEvent(E evt, C control, ControlRefs refs) {
        accept(evt);
    }
}
