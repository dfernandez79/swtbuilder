package swtbuilder;

import org.eclipse.swt.widgets.Composite;

import java.util.function.Consumer;

public class SWTBuilder {
    public static ControlRefs createChildren(Composite parent, Consumer<CompositeBuilder> fn) {
        return createChildren(parent, formLayout(), fn);
    }

    public static FormLayoutDescription formLayout() {
        return new FormLayoutDescription();
    }

    public static FillLayoutDescription fillLayout() {
        return new FillLayoutDescription();
    }

    public static ControlRefs createChildren(Composite parent, LayoutDescription layoutDescription,
                                             Consumer<CompositeBuilder> fn) {
        ControlRefs refs = new ControlRefs();
        ChildBuilder builder = new ChildBuilder();
        fn.accept(builder);
        builder.createAndLayoutChildren(parent, layoutDescription, refs);

        return refs;
    }

    public static CompositeDescription composite() {
        return new CompositeDescription();
    }
}
