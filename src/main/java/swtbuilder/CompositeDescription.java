package swtbuilder;

import org.eclipse.swt.widgets.Composite;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CompositeDescription
        extends AbstractControlDescription<CompositeDescription, Composite> {

    private final ChildBuilder builder = new ChildBuilder();
    private LayoutDescription layoutDescription = new FormLayoutDescription();

    public CompositeDescription() {
        super(Composite::new);
    }

    @Override
    protected void setUpControl(Composite control, ControlRefs refs) {
        builder.createChildren(control, layoutDescription, refs);
    }

    public CompositeDescription setUp(BiConsumer<Composite, ControlRefs> fn) {
        addSetUpBlock(fn);
        return this;
    }

    public CompositeDescription children(Consumer<CompositeBuilder> fn) {
        fn.accept(builder);
        return this;
    }

}
