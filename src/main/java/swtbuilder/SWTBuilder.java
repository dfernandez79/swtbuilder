package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import swtbuilder.descriptions.ControlDescription;
import swtbuilder.descriptions.FormLayoutDescription;
import swtbuilder.descriptions.LayoutDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SWTBuilder {

    public static Controls createChildrenOf(Composite parent, Consumer<ControlDescriptionCollector> fn) {
        return new SWTBuilder(parent, new FormLayoutDescription()).createChildren(fn);
    }

    public static FormLayoutDescription.ControlRelativeAttachment fromBottomOf(String controlId, int offset) {
        return new FormLayoutDescription.ControlRelativeAttachment(controlId, SWT.BOTTOM, offset);
    }

    public static FormLayoutDescription.ControlRelativeAttachment fromRightOf(String controlId, int offset) {
        return new FormLayoutDescription.ControlRelativeAttachment(controlId, SWT.RIGHT, offset);
    }

    private final Composite parent;
    private final LayoutDescription layoutDescription;

    public SWTBuilder(Composite parent, LayoutDescription layoutDescription) {
        this.parent = parent;
        this.layoutDescription = layoutDescription;
    }

    public Controls createChildren(Consumer<ControlDescriptionCollector> fn) {
        ControlDescriptionCollector collector = new ControlDescriptionCollector(layoutDescription);

        fn.accept(collector);

        collector.optionalSize().ifPresent(parent::setSize);
        layoutDescription.setLayoutOf(parent);

        Controls controls = new Controls(createControls(collector));

        controls.descriptionInstancePairs().forEach(pair -> pair.setUpControl(controls));

        return controls;
    }

    private List<ControlFactoryPair<?>> createControls(ControlDescriptionCollector collector) {
        List<ControlFactoryPair<?>> list = new ArrayList<>(collector.collectedChildren().size());
        for (ControlDescription<?> desc : collector.collectedChildren()) list.add(createControl(desc));
        return list;
    }

    private <C extends Control> ControlFactoryPair<C> createControl(ControlDescription<C> desc) {
        return new ControlFactoryPair<>(desc, desc.createControl(parent));
    }

}
