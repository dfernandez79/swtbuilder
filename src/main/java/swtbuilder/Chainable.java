package swtbuilder;

import java.util.function.Consumer;

public interface Chainable<T extends Chainable<T>> {

    @SuppressWarnings("unchecked")
    default T chain(Consumer<T> fn) {
        fn.accept((T) this);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default T chain(Runnable block) {
        block.run();
        return (T) this;
    }

}
