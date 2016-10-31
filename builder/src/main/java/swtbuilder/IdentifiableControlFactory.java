package swtbuilder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class IdentifiableControlFactory implements LayoutAwareControlFactory<Control> {
    private final String id;
    private final LayoutAwareControlFactory<?> factory;

    public IdentifiableControlFactory(String id, LayoutAwareControlFactory<?> factory) {
        this.id = id;
        this.factory = factory;
    }

    @Override
    public Object layoutData(String name) {
        return factory.layoutData(name);
    }

    @Override
    public Control createControl(Composite parent, ControlRefs refs) {
        Control newControl = factory.createControl(parent, refs);

        if (refs != null) {
            refs.add(id, newControl);
        }

        return newControl;
    }
}
