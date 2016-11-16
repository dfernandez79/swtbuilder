package swtbuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableDescription extends AbstractControlDescription<TableDescription, Table> {
    // TODO Add support for images
    private final List<String> columns = new ArrayList<>();
    private final List<String[]> items = new ArrayList<>();
    private final List<EventListenerLambda<Event, Table>> eraseItemListeners = new ArrayList<>();

    public TableDescription() {
        super(Table::new);
        style(SWT.BORDER | SWT.FULL_SELECTION);
    }

    @Override
    protected void setUpControl(Table control, ControlRefs refs) {
        // TODO move to an instance variable
        control.setHeaderVisible(true);

        for (String title : columns) {
            new TableColumn(control, SWT.NONE).setText(title);
        }

        for (String[] texts : items) {
            new TableItem(control, SWT.NONE).setText(texts);
        }

        for (TableColumn col : control.getColumns()) {
            col.pack();
        }
        
        for (EventListenerLambda<Event, Table> listener : eraseItemListeners) {
            control.addListener(SWT.EraseItem, (event) -> listener.handleEvent(event, control, refs));
        }
    }

    public TableDescription item(String... values) {
        items.add(values);
        return this;
    }

    public TableDescription columns(String... names) {
        Collections.addAll(columns, names);
        return this;
    }
    
    public TableDescription onEraseItem(EventListenerLambda<Event, Table> handler) {
        eraseItemListeners.add(handler);
        return this;
    }

    public TableDescription onEraseItem(BiConsumerEventListenerLambda<Event, Table> handler) {
        eraseItemListeners.add(handler);
        return this;
    }

    public TableDescription onEraseItem(ConsumerEventListenerLambda<Event, Table> handler) {
        eraseItemListeners.add(handler);
        return this;
    }

    public TableDescription onEraseItem(NoArgsEventListenerLambda<Event, Table> handler) {
        eraseItemListeners.add(handler);
        return this;
    }
}
