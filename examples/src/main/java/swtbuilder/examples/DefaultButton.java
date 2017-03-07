package swtbuilder.examples;

import org.eclipse.swt.widgets.Shell;
import swtbuilder.ControlRefs;
import swtbuilder.SWTBuilder;

import static swtbuilder.SWTBuilder.createChildren;
import static swtbuilder.SWTBuilder.rowLayout;

/**
 * {@link SWTBuilder} equivalent of:
 *
 * <a href="http://git.eclipse.org/c/platform/eclipse.platform.swt.git/tree/examples/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet108.java">Button example snippet</a>
 */
public class DefaultButton extends Example {
    public static void main(String... args) {
        new DefaultButton().run();
    }


    @Override
    protected Shell create() {
        Shell shell = new Shell();

        ControlRefs refs = createChildren(shell, rowLayout().center(), c -> {
            c.label().text("Enter your name: ");
            c.textInput().width(100);
            c.button().text("OK").onSelection(() -> System.out.println("OK"));
            c.button("cancel").text("Cancel").onSelection(() -> System.out.println("Cancel"));
        });

        shell.setDefaultButton(refs.button("cancel"));
        shell.pack();

        return shell;
    }
}
