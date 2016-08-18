package swtbuilder;

import org.eclipse.swt.widgets.Composite;

import java.util.function.Consumer;

public class SWTBuilder {

    public static ControlRefs createChildrenOf(Composite parent, Consumer<CompositeBuilder> fn) {
        // TODO Create a CompositeDescriptionAdapter (or similar)
        ControlRefs refs = new ControlRefs();

        ChildBuilder builder = new ChildBuilder();
        fn.accept(builder);
        builder.createChildren(parent, new FormLayoutDescription(), refs);

        return refs;
    }

    public static Composite composite(Composite parent, Consumer<CompositeBuilder> fn) {
        return new CompositeDescription().children(fn).createControl(parent);
    }

}
