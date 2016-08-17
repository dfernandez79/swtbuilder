package swtbuilder;

import org.eclipse.swt.widgets.Composite;

import java.util.function.BiConsumer;

public class CompositeDescription
        extends AbstractControlDescription<CompositeDescription, Composite>
        implements CompositeBuilder {

    private final ChildBuilder builder = new ChildBuilder();

    public CompositeDescription() {
        super(Composite::new);
    }

    @Override
    public <T extends LayoutAwareControlFactory<?>> T add(T controlFactory) {
        return builder.add(controlFactory);
    }

    @Override
    protected void setUpControl(Composite control, ControlRefs refs) {
        builder.createChildren(control, refs);
    }

    public CompositeDescription setUp(BiConsumer<Composite, ControlRefs> fn) {
        addSetUpBlock(fn);
        return this;
    }

}
