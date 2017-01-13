package swtbuilder;

import org.eclipse.swt.widgets.Composite;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public abstract class AbstractCompositeDescription<D extends ControlDescription<D, C>, C extends Composite>
    extends AbstractControlDescription<D, C> {

    private final ChildBuilder builder = new ChildBuilder();
    private LayoutDescription layoutDescription = new FormLayoutDescription();

    public AbstractCompositeDescription(BiFunction<Composite, Integer, C> factory) {
        super(factory);
    }

    @Override
    protected void setUpControl(C control, ControlRefs refs) {
        builder.createAndLayoutChildren(control, layoutDescription, refs);
    }

    public D setUp(BiConsumer<C, ControlRefs> fn) {
        return chain(() -> addSetUpBlock(fn));
    }

    public D children(Consumer<CompositeBuilder> fn) {
        return chain(() -> fn.accept(builder));
    }

    public D layout(LayoutDescription layoutDescription) {
        return chain(() -> this.layoutDescription = layoutDescription);
    }
}
