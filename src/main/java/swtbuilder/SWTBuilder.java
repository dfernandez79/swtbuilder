package swtbuilder;

import org.eclipse.swt.widgets.Composite;

import java.util.function.Consumer;

public class SWTBuilder {

    public static ControlRefs createChildrenOf(Composite parent, Consumer<CompositeBuilder> fn) {
        ControlRefs refs = new ControlRefs();

        ChildBuilder builder = new ChildBuilder();
        fn.accept(builder);
        builder.createChildren(parent, refs);

        return refs;
    }

    public static Composite composite(Composite parent, Consumer<CompositeDescription> fn) {
        return new CompositeDescription().chain(fn).createControl(parent);
    }

}
