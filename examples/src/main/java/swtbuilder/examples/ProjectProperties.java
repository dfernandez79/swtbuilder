package swtbuilder.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import static swtbuilder.FormLayoutDescription.fromLeftOf;
import static swtbuilder.FormLayoutDescription.fromRightOf;
import static swtbuilder.FormLayoutDescription.fromTopOf;
import static swtbuilder.SWTBuilder.createChildren;

public class ProjectProperties extends Example {
    public static void main(String[] args) {
        new ProjectProperties().run();
    }

    @Override
    protected Shell create() {
        Shell shell = new Shell();
        shell.setSize(835, 536);
        shell.setText("Properties");

        createChildren(shell, c -> {
            c.composite("treeContainer")
                    .top(0).bottom(fromTopOf("bottomLine", 0))
                    .background(SWT.COLOR_WHITE).width(187)
                    .children(c1 -> {
                        c1.verticalSeparator("vline").right(0).top(0).bottom(0);
                        c1.textInput("filter")
                                .style(SWT.SEARCH)
                                .top(8).left(8).right(fromLeftOf("vline", 8))
                                .message("type filter text");
                    });

            c.horizontalSeparator("headerLine")
                    .left(fromRightOf("treeContainer", 0)).top(36).right(0);

            c.label("header")
                    .text("Resource")
                    .left(fromRightOf("treeContainer", 10)).top(0).right(0);

            c.horizontalSeparator("bottomLine")
                    .left(0).right(0).bottom(50);
            c.button("ok")
                    .text("OK")
                    .right(10).bottom(10).width(90);
            c.button("cancel")
                    .text("Cancel")
                    .right(fromLeftOf("ok", 10)).bottom(10).width(90);
        });

        return shell;
    }
}
