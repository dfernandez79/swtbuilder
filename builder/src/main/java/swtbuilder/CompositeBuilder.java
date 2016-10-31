package swtbuilder;

import java.util.function.BiFunction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

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

    default CompositeDescription composite() {
        return composite(null);
    }

    default CompositeDescription composite(String id) {
        return add(id, new CompositeDescription());
    }

    default ButtonDescription button() {
        return button(null);
    }

    default ButtonDescription button(String id) {
        return add(id, new ButtonDescription());
    }

    default ComboDescription dropdown() {
        return dropdown(null);
    }

    default ComboDescription dropdown(String id) {
        return combo(id).style(SWT.READ_ONLY).select(0);
    }

    default ComboDescription combo() {
        return combo(null);
    }

    default ComboDescription combo(String id) {
        return add(id, new ComboDescription());
    }

    default <C extends Control> PluggableControlDescription<C> control(
                                                                       BiFunction<Composite, Integer, C> factory) {
        return control(null, factory);
    }

    default <C extends Control> PluggableControlDescription<C> control(
                                                                       String id,
                                                                       BiFunction<Composite, Integer, C> factory) {
        return add(id, new PluggableControlDescription<>(factory));
    }

    default ButtonDescription checkbox() {
        return checkbox(null);
    }

    default ButtonDescription checkbox(String id) {
        return button(id).style(SWT.CHECK);
    }

    default LinkDescription link() {
        return link(null);
    }

    default LinkDescription link(String id) {
        return add(id, new LinkDescription());
    }

    default TextDescription textInput() {
        return textInput(null);
    }

    default TextDescription textInput(String id) {
        return add(id, new TextDescription());
    }

    default BrowserDescription browser() {
        return browser(null);
    }

    default BrowserDescription browser(String id) {
        return add(id, new BrowserDescription());
    }

    default TableDescription table() {
        return table(null);
    }

    default TableDescription table(String id) {
        return add(id, new TableDescription());
    }

    default GroupDescription group() {
        return group(null);
    }

    default GroupDescription group(String id) {
        return add(id, new GroupDescription());
    }

    default ButtonDescription radio() {
        return radio(null);
    }

    default ButtonDescription radio(String id) {
        return add(id, new ButtonDescription().style(SWT.RADIO));
    }

    default LabelDescription horizontalSeparator() {
        return horizontalSeparator(null);
    }

    default LabelDescription horizontalSeparator(String id) {
        return add(id, new LabelDescription().style(SWT.SEPARATOR | SWT.HORIZONTAL));
    }
}
