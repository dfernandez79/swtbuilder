package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class AbstractControlDescription<D extends ControlDescription, C extends Control> implements ControlDescription<D, C> {

    private final String id;
    private int style = SWT.NONE;
    private Map<String, Object> layoutData = new HashMap<>();
    private final BiFunction<Composite, Integer, C> factory;

    public AbstractControlDescription(String id,
                                      BiFunction<Composite, Integer, C> factory) {
        this.id = id;
        this.factory = factory;
    }

    @Override
    public D style(int style) {
        return chain(() -> this.style = style);
    }

    @Override
    public C createControl(Composite parent, Map<String, Control> refs) {
        C control = factory.apply(parent, style);

        if (id != null && refs != null) {
            refs.put(id, control);
        }

        setUpControl(control, refs);

        return control;
    }

    protected abstract void setUpControl(C control, Map<String, Control> controlMap);

    @Override
    public Map<String, Object> layoutData() {
        return layoutData;
    }

    @Override
    public D top(Object value) {
        return chain(() -> layoutData.put("top", value));
    }

    @Override
    public D left(Object value) {
        return chain(() -> layoutData.put("left", value));
    }

    @Override
    public D right(Object value) {
        return chain(() -> layoutData.put("right", value));
    }

    @Override
    public D bottom(Object value) {
        return chain(() -> layoutData.put("bottom", value));
    }

}
