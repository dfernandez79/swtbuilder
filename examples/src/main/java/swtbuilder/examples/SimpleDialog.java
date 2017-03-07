package swtbuilder.examples;

import static swtbuilder.FormLayoutDescription.fromBottomOf;
import static swtbuilder.FormLayoutDescription.fromLeftOf;
import static swtbuilder.FormLayoutDescription.fromTopOf;
import static swtbuilder.SWTBuilder.createChildren;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import swtbuilder.SWTBuilder;

/**
 * {@link SWTBuilder} equivalent of:
 *
 * <a href="http://git.eclipse.org/c/platform/eclipse.platform.swt.git/tree/examples/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet65.java">FormLayout example snippet</a>
 */
public class SimpleDialog extends Example {
    public static void main(String[] args) {
        new SimpleDialog().run();
    }

    @Override
    protected Shell create() {
        Shell shell = new Shell();

        createChildren(shell, c -> {
            c.label("msg")
                    .style(SWT.WRAP)
                    .left(4)
                    .right(4)
                    .text("This is a long text string that will wrap when the dialog is resized.");

            c.list()
                    .left(4)
                    .right(4)
                    .top(fromBottomOf("msg", 4))
                    .bottom(fromTopOf("cancel", 4))
                    .item("Item 1")
                    .item("Item 2");

            c.button()
                    .right(fromLeftOf("cancel", 4))
                    .bottom(0)
                    .text("OK");

            c.button("cancel")
                    .right(4)
                    .bottom(0)
                    .text("Cancel");
        });

        shell.pack();

        return shell;
    }
}
