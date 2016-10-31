package swtbuilder;

import org.eclipse.swt.widgets.Composite;

public class CompositeDescription extends AbstractCompositeDescription<CompositeDescription, Composite> {
    public CompositeDescription() {
        super(Composite::new);
    }
}
