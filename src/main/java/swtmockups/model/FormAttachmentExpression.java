package swtmockups.model;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Control;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.empty;

public class FormAttachmentExpression {
    public final Optional<String> controlId;
    public final Optional<Integer> alignment;
    public final Optional<Integer> denominator;
    public final Optional<Integer> numerator;
    public final Optional<Integer> offset;

    public FormAttachmentExpression(Optional<String> controlId,
                                    Optional<Integer> alignment,
                                    Optional<Integer> denominator,
                                    Optional<Integer> numerator,
                                    Optional<Integer> offset) {
        this.controlId = controlId;
        this.alignment = alignment;
        this.denominator = denominator;
        this.numerator = numerator;
        this.offset = offset;
    }

    public FormAttachmentExpression(int offset) {
        this(empty(), empty(), empty(), empty(), Optional.of(offset));
    }

    public FormAttachmentExpression(String controlId) {
        this(Optional.of(controlId), empty(), empty(), empty(), empty());
    }

    public FormAttachmentExpression(String controlId, int offset) {
        this(Optional.of(controlId), empty(), empty(), empty(), Optional.of(offset));
    }

    public FormAttachmentExpression(int numerator, int offset) {
        this(empty(), empty(), empty(), Optional.of(numerator), Optional.of(offset));
    }

    public FormAttachment createAttachment(Map<String, Control> controls) {
        // FormAttachment(Control control, int offset, int alignment)
        if (controlId.isPresent() && offset.isPresent() && alignment.isPresent()) {
            return new FormAttachment(controls.get(controlId.get()), offset.get(), alignment.get());
        }

        // FormAttachment(Control control, int offset)
        if (controlId.isPresent() && offset.isPresent()) {
            return new FormAttachment(controls.get(controlId.get()), offset.get());
        }

        // FormAttachment(Control control)
        if (controlId.isPresent()) {
            return new FormAttachment(controls.get(controlId.get()));
        }

        // FormAttachment(int numerator, int denominator, int offset)
        if (numerator.isPresent() && denominator.isPresent() && offset.isPresent()) {
            return new FormAttachment(numerator.get(), denominator.get(), offset.get());
        }

        // FormAttachment(int numerator, int offset)
        if (numerator.isPresent() && offset.isPresent()) {
            return new FormAttachment(numerator.get(), offset.get());
        }

        // FormAttachment(int numerator)
        if (numerator.isPresent()) {
            return new FormAttachment(numerator.get());
        }

        if (offset.isPresent()) {
            return new FormAttachment(0, offset.get());
        }

        return new FormAttachment();
    }
}
