package swtbuilder;

import org.eclipse.swt.SWT;

import java.util.function.Consumer;

public interface CompositeBuilder {

    <T extends LayoutAwareControlFactory<?>> T add(T controlFactory);

    default <T extends LayoutAwareControlFactory<?>> T add(String id, T controlFactory) {
        if (id != null) {
            add(new IdentifiableControlFactory(id, controlFactory));
        } else {
            add(controlFactory);
        }
        return controlFactory;
    }

    default LabelDescription label() {
        return label(null);
    }

    default LabelDescription label(String id) {
        return add(id, new LabelDescription());
    }

    default CompositeDescription composite(Consumer<CompositeBuilder> fn) {
        return composite(null, fn);
    }

    default CompositeDescription composite(String id, Consumer<CompositeBuilder> fn) {
        return add(id, new CompositeDescription().chain(fn::accept));
    }

    void size(int width, int height);

    default ButtonDescription button() {
        return button(null);
    }

    default ButtonDescription button(String id) {
        return add(id, new ButtonDescription());
    }

    default ComboDescription dropdown(String id) {
        return combo(id).style(SWT.READ_ONLY).select(0);
    }

    default ComboDescription combo(String id) {
        return add(id, new ComboDescription());
    }

}
