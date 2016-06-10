package swtmockups.model

import groovy.transform.Immutable
import groovy.transform.TupleConstructor
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FormAttachment
import org.eclipse.swt.widgets.Control

@Immutable
class FormAttachmentDescription {

    @TupleConstructor
    static enum Alignment {
        TOP(SWT.TOP),
        BOTTOM(SWT.BOTTOM),
        LEFT(SWT.LEFT),
        RIGHT(SWT.RIGHT),
        CENTER(SWT.CENTER),
        DEFAULT(SWT.DEFAULT);

        int value
    }


    String controlId
    int offset
    Alignment alignment = Alignment.DEFAULT

    static FormAttachmentDescription from(String controlId, int offset = 0) {
        new FormAttachmentDescription(controlId, offset, Alignment.DEFAULT)
    }

    static FormAttachmentDescription topOf(String controlId, int offset = 0) {
        new FormAttachmentDescription(controlId, offset, Alignment.TOP)
    }


    FormAttachment createAttachment(String side, Map<String, Control> controls) {
        boolean invert = ('right'.equalsIgnoreCase(side) || 'bottom'.equalsIgnoreCase(side))
        int signedOffset = (invert ? -1 : 1) * offset

        if (controlId) {
            new FormAttachment(controls[controlId], signedOffset, alignment.value)
        } else {
            int numerator = invert ? 100 : 0
            new FormAttachment(numerator, signedOffset)
        }
    }

}
