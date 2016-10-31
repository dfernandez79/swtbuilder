package swtbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public abstract class AbstractControlDescription<D extends ControlDescription<D, C>, C extends Control>
    implements ControlDescription<D, C> {

    private int style = SWT.NONE;
    private final Map<String, Object> layoutData = new HashMap<>();
    private final List<BiConsumer<C, ControlRefs>> setUpBlocks = new ArrayList<>();
    private final BiFunction<Composite, Integer, C> factory;
    private Integer width = null;
    private Integer height = null;
    private Integer backgroundColor = null;

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

        if (width != null && height != null) {
            control.setSize(width, height);
        }

        if (backgroundColor != null) {
            control.setBackground(Display.getCurrent().getSystemColor(backgroundColor));
        }

        setUpControl(control, refs);
        applySetUpBlock(control, refs);

        return control;
    }

    protected void applySetUpBlock(C control, ControlRefs refs) {
        setUpBlocks.forEach(c -> c.accept(control, refs));
    }

    @Override
    public D setUp(Consumer<C> fn) {
        return chain(() -> addSetUpBlock((control, refs) -> fn.accept(control)));
    }

    protected void addSetUpBlock(BiConsumer<C, ControlRefs> fn) {
        setUpBlocks.add(fn);
    }

    protected abstract void setUpControl(C control, ControlRefs refs);

    @Override
    public D background(int systemColor) {
        return chain(() -> backgroundColor = systemColor);
    }

    @Override
    public D width(int width) {
        return chain(() -> {
            this.width = width;
            layoutData("width", width);
        });
    }

    @Override
    public D height(int height) {
        return chain(() -> {
            this.height = height;
            layoutData("height", height);
        });
    }

    @Override
    public D layoutData(String name, Object value) {
        return chain(() -> layoutData.put(name, value));
    }

    @Override
    public Object layoutData(String name) {
        return layoutData.get(name);
    }
}
