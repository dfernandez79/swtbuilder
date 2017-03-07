package swtbuilder;

import org.eclipse.swt.widgets.Composite;

import java.util.function.Consumer;

public final class SWTBuilder {
    private SWTBuilder() {}

    public static ControlRefs createChildren(Composite parent, Consumer<CompositeBuilder> fn) {
        return createChildren(parent, formLayout(), fn);
    }

    public static FormLayoutDescription formLayout() {
        return new FormLayoutDescription();
    }

    public static FillLayoutDescription fillLayout() {
        return new FillLayoutDescription();
    }

    public static RowLayoutDescription rowLayout() {
        return new RowLayoutDescription();
    }

    public static StackLayoutDescription stackLayout() {
        return stackLayout(null);
    }

    public static StackLayoutDescription stackLayout(String topControlId) {
        return stackLayout(topControlId, 0, 0);
    }

    public static StackLayoutDescription stackLayout(String topControlId, int marginWidth, int marginHeight) {
        return new StackLayoutDescription(topControlId, marginWidth, marginHeight);
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
