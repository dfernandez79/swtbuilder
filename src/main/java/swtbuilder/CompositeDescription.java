package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.Map;

public class CompositeDescription
        extends AbstractControlDescription<CompositeDescription, Composite>
        implements CompositeBuilder {

    private ChildBuilder builder = new ChildBuilder();
    private LayoutDescription layoutDescription = new FormLayoutDescription();

    public CompositeDescription() {
        super(Composite::new);
    }

    @Override
    public <T extends LayoutAwareControlFactory> T add(T controlFactory) {
        return builder.add(controlFactory);
    }

    @Override
    protected void setUpControl(Composite control, Map<String, Control> refs) {
        builder.createChildren(control, layoutDescription, refs);
    }

}
