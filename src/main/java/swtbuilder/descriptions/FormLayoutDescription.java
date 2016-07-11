package swtbuilder.descriptions;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import swtbuilder.Controls;

import java.util.function.Function;

public class FormLayoutDescription implements LayoutDescription {

    @Override
    public LayoutDataDescription newLayoutDataDescription() {
        return new FormLayoutDataDescription();
    }

    @Override
    public void setLayoutOf(Composite parent) {
        parent.setLayout(new FormLayout());
    }

    public static class ControlRelativeAttachment {
        private final String controlId;
        private final int alignment;
        private final int offset;

        public ControlRelativeAttachment(String controlId, int alignment, int offset) {
            this.controlId = controlId;
            this.alignment = alignment;
            this.offset = offset;
        }

        public FormAttachment createAttachment(Controls controls) {
            return new FormAttachment(controls.get(controlId), offset, alignment);
        }
    }

    private static class FormLayoutDataDescription implements LayoutDataDescription {
        private Function<Controls, FormAttachment> top = m -> null;
        private Function<Controls, FormAttachment> right = m -> null;
        private Function<Controls, FormAttachment> left = m -> null;
        private Function<Controls, FormAttachment> bottom = m -> null;

        @Override
        public LayoutDataDescription set(String name, Object value) {
            if ("top".equals(name)) {
                top = factoryFor(value, false);
            } else if ("right".equals(name)) {
                right = factoryFor(value, true);
            } else if ("bottom".equals(name)) {
                bottom = factoryFor(value, true);
            } else if ("left".equals(name)) {
                left = factoryFor(value, false);
            }
            return this;
        }

        private Function<Controls, FormAttachment> factoryFor(final Object value, boolean negate) {
            if (value instanceof Number) {
                int sign = negate ? -1 : 1;
                return m -> new FormAttachment(0, sign * ((Number) value).intValue());
            } else if (value instanceof ControlRelativeAttachment) {
                return ((ControlRelativeAttachment) value)::createAttachment;
            }

            return m -> null;
        }

        @Override
        public void applyLayout(Controls controls, Control instance) {
            FormData data = new FormData();
            data.top = top.apply(controls);
            data.right = right.apply(controls);
            data.bottom = bottom.apply(controls);
            data.left = left.apply(controls);
            instance.setLayoutData(data);
        }
    }

}
