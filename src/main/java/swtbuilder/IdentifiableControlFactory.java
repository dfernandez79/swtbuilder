package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import java.util.Map;

public class IdentifiableControlFactory implements LayoutAwareControlFactory<Control> {

    private final String id;
    private final LayoutAwareControlFactory<?> factory;

    public IdentifiableControlFactory(String id, LayoutAwareControlFactory<?> factory) {
        this.id = id;
        this.factory = factory;
    }

    @Override
    public void layoutData(String name, Object value) {
        factory.layoutData(name, value);
    }

    @Override
    public Object layoutData(String name) {
        return factory.layoutData(name);
    }

    @Override
    public Control createControl(Composite parent, Map<String, Control> refs) {
        Control newControl = factory.createControl(parent, refs);

        if (id != null && refs != null) {
            refs.put(id, newControl);
        }

        return newControl;
    }

}
