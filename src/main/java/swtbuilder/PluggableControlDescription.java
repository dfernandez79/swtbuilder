package swtbuilder;

import java.util.function.BiFunction;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class PluggableControlDescription<C extends Control>
    extends AbstractControlDescription<PluggableControlDescription<C>, C> {

    public PluggableControlDescription(BiFunction<Composite, Integer, C> factory) {
        super(factory);
    }

    @Override
    protected void setUpControl(C control, ControlRefs refs) {}
}
