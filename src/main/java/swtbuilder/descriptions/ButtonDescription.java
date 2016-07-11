package swtbuilder.descriptions;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import swtbuilder.Controls;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ButtonDescription extends GenericControlDescription<Button> {

    private final List<Function<Controls, SelectionListener>> selectionListenerFactories = new ArrayList<>();

    public ButtonDescription(String id,
                             LayoutDataDescription layoutDataDescription) {
        super(id, layoutDataDescription, Button::new);
    }

    public ButtonDescription selection(BiConsumer<Controls, SelectionEvent> handler) {
        selectionListenerFactories.add(controls -> new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                handler.accept(controls, e);
            }
        });
        return this;
    }

    @Override
    public Button setUpControl(Controls controls, Button control) {
        super.setUpControl(controls, control);
        selectionListenerFactories.forEach(factory -> control.addSelectionListener(factory.apply(controls)));
        return control;
    }

}
