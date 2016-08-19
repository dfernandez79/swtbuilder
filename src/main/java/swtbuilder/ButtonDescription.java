package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;
import java.util.List;

public class ButtonDescription extends AbstractControlDescription<ButtonDescription, Button> {

    private String text;
    private final List<EventListenerLambda<SelectionEvent, Button>> selectionListeners = new ArrayList<>();
    private boolean selected;

    public ButtonDescription() {
        super(Button::new);
    }

    @Override
    protected void setUpControl(Button control, ControlRefs refs) {
        if (text != null) {
            control.setText(text);
        }

        if ((control.getStyle() & SWT.CHECK) == SWT.CHECK || (control.getStyle() & SWT.RADIO) == SWT.RADIO) {
            control.setSelection(selected);
        }

        selectionListeners.forEach(listener -> control.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listener.handleEvent(e, control, refs);
            }
        }));
    }

    public ButtonDescription text(String text) {
        this.text = text;
        return this;
    }

    public ButtonDescription onSelection(EventListenerLambda<SelectionEvent, Button> handler) {
        selectionListeners.add(handler);
        return this;
    }

    public ButtonDescription onSelection(BiConsumerEventListenerLambda<SelectionEvent, Button> handler) {
        selectionListeners.add(handler);
        return this;
    }

    public ButtonDescription onSelection(ConsumerEventListenerLambda<SelectionEvent, Button> handler) {
        selectionListeners.add(handler);
        return this;
    }

    public ButtonDescription onSelection(NoArgsEventListenerLambda<SelectionEvent, Button> handler) {
        selectionListeners.add(handler);
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
