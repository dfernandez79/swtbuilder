package swtmockups.model;

import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;

import java.util.Map;
import java.util.Optional;

public class FormDataExpressions {
    private final Optional<FormAttachmentExpression> top;
    private final Optional<FormAttachmentExpression> right;
    private final Optional<FormAttachmentExpression> bottom;
    private final Optional<FormAttachmentExpression> left;


    public FormDataExpressions(Optional<FormAttachmentExpression> top,
                               Optional<FormAttachmentExpression> right,
                               Optional<FormAttachmentExpression> bottom,
                               Optional<FormAttachmentExpression> left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public FormData createFormData(Map<String, Control> controls) {
        FormData data = new FormData();

        top.ifPresent(expression -> data.top = expression.createAttachment(controls));
        right.ifPresent(expression -> data.right = expression.createAttachment(controls));
        bottom.ifPresent(expression -> data.bottom = expression.createAttachment(controls));
        left.ifPresent(expression -> data.left = expression.createAttachment(controls));

        return data;
    }
}
