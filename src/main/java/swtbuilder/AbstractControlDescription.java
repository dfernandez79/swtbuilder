package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class AbstractControlDescription<D extends ControlDescription<D, C>, C extends Control> implements ControlDescription<D, C> {

    private int style = SWT.NONE;
    private final Map<String, Object> layoutData = new HashMap<>();
    private final BiFunction<Composite, Integer, C> factory;

    public AbstractControlDescription(BiFunction<Composite, Integer, C> factory) {
        this.factory = factory;
    }

    @Override
    public D style(int style) {
        return chain(() -> this.style = style);
    }

    @Override
    public C createControl(Composite parent, ControlRefs refs) {
        C control = factory.apply(parent, style);

        setUpControl(control, refs);

        return control;
    }

    protected abstract void setUpControl(C control, ControlRefs refs);

    @Override
    public void layoutData(String name, Object value) {
        layoutData.put(name, value);
    }

    @Override
    public Object layoutData(String name) {
        return layoutData.get(name);
    }

}
