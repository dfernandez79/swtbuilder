package swtbuilder.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import swtbuilder.CompositeDescription;

import static swtbuilder.FormLayoutDescription.fromLeftOf;
import static swtbuilder.FormLayoutDescription.fromRightOf;
import static swtbuilder.FormLayoutDescription.fromTopOf;
import static swtbuilder.FormLayoutDescription.fromBottomOf;
import static swtbuilder.SWTBuilder.createChildren;
import static swtbuilder.SWTBuilder.composite;

public class ProjectProperties extends Example {
    public static void main(String[] args) {
        new ProjectProperties().run();
    }

    @Override
    protected Shell create() {
        Shell shell = new Shell();
        shell.setSize(1566 / 2, 1070 / 2);
        shell.setText("Properties");

        createChildren(shell, c -> {
            c.composite("treeContainer")
                    .top(0)
                    .bottom(fromTopOf("bottomLine", 0))
                    .background(SWT.COLOR_WHITE)
                    .width(187)
                    .children(c1 -> {
                        c1.verticalSeparator("vline").right(0).top(0).bottom(0);
                        c1.textInput("filter")
                                .style(SWT.SEARCH)
                                .top(8)
                                .left(8)
                                .right(fromLeftOf("vline", 8))
                                .message("type filter text");
                    });

            c.horizontalSeparator("headerLine")
                    .left(fromRightOf("treeContainer", 0))
                    .top(36)
                    .right(0);

            c.label("header")
                    .text("Resource")
                    .left(fromRightOf("treeContainer", 10))
                    .top(0)
                    .right(0);

            c.add(propertiesPageDescription())
                    .top(fromBottomOf("headerLine", 0))
                    .left(fromRightOf("treeContainer", 0))
                    .bottom(fromTopOf("bottomLine", 0))
                    .right(0);

            c.horizontalSeparator("bottomLine")
                    .left(0)
                    .right(0)
                    .bottom(50);
            c.button("ok")
                    .text("OK")
                    .right(10)
                    .bottom(10)
                    .width(90);
            c.button("cancel")
                    .text("Cancel")
                    .right(fromLeftOf("ok", 10))
                    .bottom(10)
                    .width(90);
        });

        return shell;
    }

    private CompositeDescription propertiesPageDescription() {
        Color tableGray = new Color(Display.getCurrent(), 245, 245, 245);

        return composite()
                .children(c -> {
                    c.table("list")
                            .left(10)
                            .top(10)
                            // .bottom(fromBottomOf("remove", -7))
                            .height(175)
                            .right(fromLeftOf("remove", 10))
                            .columns("Name", "Version")
                            .item("Database", "1.0.0")
                            .item("Email", "1.0.0")
                            .item("File", "1.0.0")
                            .item("FTP", "1.0.0")
                            .item("HTTP", "1.0.0")
                            .item("Sockets", "1.0.0")
                            .item("Validation", "1.0.0")
                            .onEraseItem((evt, table) -> {
                                int index = table.indexOf((TableItem) evt.item);
                                if (index % 2 == 0) {
                                    Color oldBackground = evt.gc.getBackground();
                                    evt.gc.setBackground(tableGray);
                                    evt.gc.fillRectangle(0, evt.y, table.getClientArea().width, evt.height);
                                    evt.gc.setBackground(oldBackground);
                                }
                            })
                            .onDispose(() -> tableGray.dispose());

                    c.button("add")
                            .text("Add Extension...")
                            .width(120)
                            .top(7)
                            .right(5);

                    c.button("update")
                            .text("Update")
                            .top(fromBottomOf("add", 20))
                            .width(120)
                            .right(5);

                    c.button("remove")
                            .text("Remove")
                            .top(fromBottomOf("update", 0))
                            .width(120)
                            .right(5);

                    c.composite()
                            .background(SWT.COLOR_WHITE)
                            .top(fromBottomOf("list", 10))
                            .bottom(fromTopOf("apply", 10))
                            .left(10)
                            .right(10)
                            .children(p -> {
                                p.combo()
                                        .style(SWT.READ_ONLY)
                                        .items("1.0.0", "2.0.0")
                                        .select(0)
                                        .right(5)
                                        .top(5)
                                        .width(100);
                            });

                    c.button("apply")
                            .text("Apply")
                            .bottom(10)
                            .right(5)
                            .width(90);
                });
    }
}
