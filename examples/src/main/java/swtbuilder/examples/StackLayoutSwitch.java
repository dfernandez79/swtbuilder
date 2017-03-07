package swtbuilder.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;
import swtbuilder.SWTBuilder;

import static swtbuilder.SWTBuilder.createChildren;
import static swtbuilder.SWTBuilder.rowLayout;
import static swtbuilder.SWTBuilder.stackLayout;

/**
 * {@link SWTBuilder} equivalent of:
 *
 * <a href="http://git.eclipse.org/c/platform/eclipse.platform.swt.git/tree/examples/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet249.java">StackLayout example snippet</a>
 */
public class StackLayoutSwitch extends Example {

    private int pageNum = -1;

    public static void main(String[] args) {
        new StackLayoutSwitch().run();
    }

    @Override
    protected Shell create() {
        Shell shell = new Shell();
        shell.setSize(300, 200);

        createChildren(shell, c -> {
            c.composite("contentPanel")
                    .layout(stackLayout())
                    .style(SWT.BORDER)
                    .left(100)
                    .top(10)
                    .width(190)
                    .height(90)
                    .children(cp -> {
                        cp.composite("page1")
                                .layout(rowLayout())
                                .children(page1 -> page1.label().text("Label on page 1"));
                        cp.composite("page2")
                                .layout(rowLayout())
                                .children(page2 -> page2.button().text("Button on page 2"));
                    });

            c.button()
                    .text("Push")
                    .left(10)
                    .top(10)
                    .width(80)
                    .height(25)
                    .onSelection((evt, ctrl, refs) -> {
                        pageNum = ++pageNum % 2;
                        Composite contentPanel = refs.composite("contentPanel");

                        ((StackLayout) contentPanel.getLayout()).topControl = pageNum == 0
                                ? refs.get("page1")
                                : refs.get("page2");

                        contentPanel.layout();
                    });
        });

        return shell;
    }
}
