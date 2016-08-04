package swtbuilder;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Control;

import java.util.Map;
import java.util.Optional;

public class FormLayoutDescription implements LayoutDescription {

    @Override
    public FormLayout createLayout() {
        return new FormLayout();
    }

    @Override
    public void layoutControl(ControlDescription<?, ?> description, Control control, Map<String, Control> refs) {
        FormData formData = new FormData();
        control.setLayoutData(formData);

        Map<String, Object> layoutData = description.layoutData();
        createAttachment(refs, layoutData.get("top"), false).ifPresent(a -> formData.top = a);
        createAttachment(refs, layoutData.get("left"), false).ifPresent(a -> formData.left = a);
        createAttachment(refs, layoutData.get("right"), true).ifPresent(a -> formData.right = a);
        createAttachment(refs, layoutData.get("bottom"), true).ifPresent(a -> formData.bottom = a);
    }

    private Optional<FormAttachment> createAttachment(Map<String, Control> refs, Object value, boolean negate) {
        if (value == null) {
            return Optional.empty();
        }

        if (value instanceof Number) {
            int sign = negate ? -1 : 1;
            return Optional.of(new FormAttachment(negate ? 100 : 0, sign * ((Number) value).intValue()));
        }

        return Optional.empty();
    }

}
