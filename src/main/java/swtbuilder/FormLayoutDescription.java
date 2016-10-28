package swtbuilder;

import java.util.Optional;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Control;

public class FormLayoutDescription implements LayoutDescription {
    public static ControlAttachment fromRightOf(String id, int offset) {
        return new ControlAttachment(id, offset, SWT.RIGHT);
    }

    public static ControlAttachment fromBottomOf(String id, int offset) {
        return new ControlAttachment(id, offset, SWT.BOTTOM);
    }

    public static ControlAttachment fromLeftOf(String id, int offset) {
        return new ControlAttachment(id, -offset, SWT.LEFT);
    }

    public static ControlAttachment fromTopOf(String id, int offset) {
        return new ControlAttachment(id, -offset, SWT.TOP);
    }

    public static ControlAttachment fromMiddleOf(String id, int offset) {
        return new ControlAttachment(id, offset, SWT.CENTER);
    }

    @Override
    public FormLayout createLayout() {
        return new FormLayout();
    }

    @Override
    public void layoutControl(Control control, LayoutDataDescription layoutDataDescription, ControlRefs refs) {
        FormData formData = new FormData();
        control.setLayoutData(formData);

        intValueFrom(layoutDataDescription.layoutData("width")).ifPresent(n -> formData.width = n);
        intValueFrom(layoutDataDescription.layoutData("height")).ifPresent(n -> formData.height = n);

        createAttachment(refs, layoutDataDescription.layoutData("top"), false)
                .ifPresent(a -> formData.top = a);
        createAttachment(refs, layoutDataDescription.layoutData("left"), false)
                .ifPresent(a -> formData.left = a);
        createAttachment(refs, layoutDataDescription.layoutData("right"), true)
                .ifPresent(a -> formData.right = a);
        createAttachment(refs, layoutDataDescription.layoutData("bottom"), true)
                .ifPresent(a -> formData.bottom = a);
    }

    private Optional<FormAttachment> createAttachment(ControlRefs refs, Object value, boolean negate) {
        int sign = negate ? -1 : 1;
        Optional<FormAttachment> result =
                intValueFrom(value).map(num -> new FormAttachment(negate ? 100 : 0, sign * num));

        if (!result.isPresent()) {
            result = controlAttachmentFrom(refs, value);
        }

        return result;
    }

    private Optional<FormAttachment> controlAttachmentFrom(ControlRefs refs, Object value) {
        return (value != null && value instanceof ControlAttachment)
                ? Optional.of(((ControlAttachment) value).createFormAttachment(refs))
                : Optional.empty();
    }

    private Optional<Integer> intValueFrom(Object obj) {
        return (obj != null && obj instanceof Number)
                ? Optional.of(((Number) obj).intValue())
                : Optional.empty();
    }

    public static class ControlAttachment {
        private final String id;
        private final int offset;
        private final int alignment;

        public ControlAttachment(String id, int offset, int alignment) {
            this.id = id;
            this.offset = offset;
            this.alignment = alignment;
        }

        public FormAttachment createFormAttachment(ControlRefs refs) {
            return new FormAttachment(refs.get(id), offset, alignment);
        }
    }
}
