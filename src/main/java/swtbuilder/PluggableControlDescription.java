package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class PluggableControlDescription<C extends Control>
        extends AbstractControlDescription<PluggableControlDescription<C>, C> {

    private final List<Consumer<C>> setUpBlocks = new ArrayList<>();

    public PluggableControlDescription(BiFunction<Composite, Integer, C> factory) {
        super(factory);
    }

    @Override
    protected void setUpControl(C control, ControlRefs refs) {
        setUpBlocks.forEach(c -> c.accept(control));
    }

    public PluggableControlDescription<C> setUp(Consumer<C> fn) {
        setUpBlocks.add(fn);
        return this;
    }

}
