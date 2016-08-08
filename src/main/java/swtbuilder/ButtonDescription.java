package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class ButtonDescription extends AbstractControlDescription<ButtonDescription, Button> {

    private String text;
    private final List<Function<ControlRefs, SelectionListener>> selectionListenerFactories = new ArrayList<>();
    private boolean selected;

    public ButtonDescription() {
        super(Button::new);
    }

    @Override
    protected void setUpControl(Button control, ControlRefs refs) {
        if (text != null) {
            control.setText(text);
        }

        if ((control.getStyle() & SWT.CHECK) == SWT.CHECK) {
            control.setSelection(selected);
        }

        selectionListenerFactories.forEach(factory -> control.addSelectionListener(factory.apply(refs)));
    }

    public ButtonDescription text(String text) {
        this.text = text;
        return this;
    }

    public ButtonDescription onSelection(BiConsumer<ControlRefs, SelectionEvent> handler) {
        selectionListenerFactories.add(controls -> new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handler.accept(controls, e);
            }
        });
        return this;
    }

    public ButtonDescription onSelection(Consumer<ControlRefs> handler) {
        selectionListenerFactories.add(controls -> new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handler.accept(controls);
            }
        });
        return this;
    }


    public ButtonDescription selected() {
        return selected(true);
    }

    public ButtonDescription selected(boolean value) {
        selected = value;
        return this;
    }

}
