package swtbuilder.descriptions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import swtbuilder.Controls;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public abstract class GenericControlDescription<C extends Control> implements ControlDescription<C> {

    private final String id;
    private final LayoutDataDescription layoutDataDescription;
    private final BiFunction<Composite, Integer, C> factory;
    private final Map<String, Object> properties = new HashMap<>();
    private int style = SWT.NONE;

    public GenericControlDescription(String id,
                                     LayoutDataDescription layoutDataDescription,
                                     BiFunction<Composite, Integer, C> factory) {
        this.id = id;
        this.layoutDataDescription = layoutDataDescription;
        this.factory = factory;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public GenericControlDescription<C> style(int style) {
        return chain(desc -> this.style = style);
    }

    @Override
    public GenericControlDescription<C> set(String propertyName, Object value) {
        return chain(desc -> properties.put(propertyName, value));
    }

    @Override
    public GenericControlDescription<C> top(Object value) {
        return chain(desc -> layoutDataDescription.set("top", value));
    }

    @Override
    public GenericControlDescription<C> left(Object value) {
        return chain(desc -> layoutDataDescription.set("left", value));
    }

    @Override
    public GenericControlDescription<C> right(int value) {
        return chain(desc -> layoutDataDescription.set("right", value));
    }

    @Override
    public GenericControlDescription<C> bottom(int value) {
        return chain(desc -> layoutDataDescription.set("bottom", value));
    }

    @Override
    public GenericControlDescription<C> chain(Consumer<ControlDescription<C>> fn) {
        fn.accept(this);
        return this;
    }

    public GenericControlDescription<C> text(String text) {
        return set("text", text);
    }

    @Override
    public C createControl(Composite parent) {
        return factory.apply(parent, style);
    }

    @Override
    public C setUpControl(Controls controls, C control) {
        setUpProperties(control);
        layoutDataDescription.applyLayout(controls, control);
        return control;
    }

    private void setUpProperties(C control) {
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            invokeSetter(control, entry.getKey(), entry.getValue());
        }
    }

    private void invokeSetter(C control, String name, Object value) {
        try {
            String setterName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            Method method = control.getClass().getMethod(setterName, value.getClass());
            method.invoke(control, value);
        } catch (Exception e) {
            // TODO throw a proper exception
            throw new RuntimeException(e);
        }
    }

}
