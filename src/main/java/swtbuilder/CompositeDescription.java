package swtbuilder;

import org.eclipse.swt.widgets.Composite;

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
    public void size(int width, int height) {
        builder.size(width, height);
    }

    @Override
    protected void setUpControl(Composite control, ControlRefs refs) {
        builder.createChildren(control, refs);
    }

}
