package swtbuilder.examples;

import static swtbuilder.FormLayoutDescription.fromBottomOf;
import static swtbuilder.FormLayoutDescription.fromLeftOf;
import static swtbuilder.FormLayoutDescription.fromRightOf;
import static swtbuilder.FormLayoutDescription.fromTopOf;
import static swtbuilder.SWTBuilder.composite;
import static swtbuilder.SWTBuilder.createChildren;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import swtbuilder.CompositeDescription;

// TODO Move the modules properties out of the example
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
            c.add("treeContainer", propertiesTree())
                    .top(0)
                    .width(187)
                    .bottom(fromTopOf("bottomLine", 0));

            c.horizontalSeparator("headerLine")
                    .left(fromRightOf("treeContainer", 0))
                    .top(36)
                    .right(0);

            c.label("header")
                    .text("Project Modules")
                    .top(6)
                    .left(fromRightOf("treeContainer", 10))
                    .font(12, SWT.BOLD);

            c.add(propertiesPageDescription(shell))
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
                    .width(90)
                    .onSelection(shell::close);
        });

        return shell;
    }

    private CompositeDescription propertiesTree() {
        return composite()
                .background(SWT.COLOR_WHITE)
                .children(c -> {
                    c.verticalSeparator("vline").right(0).top(0).bottom(0);

                    c.textInput("filter")
                            .style(SWT.SEARCH)
                            .top(8)
                            .left(8)
                            .right(fromLeftOf("vline", 8))
                            .message("type filter text");

                    c.tree()
                            .top(fromBottomOf("filter", 4))
                            .left(10)
                            .right(fromLeftOf("vline", 8))
                            .bottom(0)
                            .item("Resource", e -> {
                                e.item("Linked Resources");
                                e.item("Resource Filters");
                            })
                            .item("Builders")
                            .item("Java Build Path")
                            .item("Java Code Style", e -> {
                                e.item("Clean Up");
                                e.item("Code Templates");
                                e.item("Formatter");
                                e.item("Organize Imports");
                            })
                            .item("Java Compiler", e -> {
                                e.item("Annotation Processing", e2 -> {
                                    e2.item("Factory Path");
                                });
                                e.item("Building");
                                e.item("Errors/Warnings");
                                e.item("Javadoc");
                                e.item("Task Tag");
                            })
                            .item("Java Editor")
                            .item("Javadoc Location")
                            .item("Project References")
                            .item("Run/Debug Settings")
                            .item("Server")
                            .item("Task Tags")
                            .item("Validation");
                });
    }

    private void showAddModule(Shell shell) {
        Color tableGray = new Color(Display.getCurrent(), 245, 245, 245);
        Shell dialog = new Shell(shell, SWT.SHEET | SWT.APPLICATION_MODAL | SWT.RESIZE);

        createChildren(dialog, c -> {
            c.textInput("searchInput")
                    .style(SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL)
                    .left(10)
                    .top(10)
                    .right(10);

            c.table("list")
                    .top(fromBottomOf("searchInput", 10))
                    .left(10)
                    .right(10)
                    .columns("Name", "Latest Version")
                    .item("MongoDB", "1.0.0")
                    .item("Scripting", "1.1.0")
                    .bottom(fromTopOf("add", 10))
                    .onEraseItem((evt, table) -> {
                        int index = table.indexOf((TableItem) evt.item);
                        if (index % 2 == 0) {
                            Color oldBackground = evt.gc.getBackground();
                            evt.gc.setBackground(tableGray);
                            evt.gc.fillRectangle(0, evt.y, table.getClientArea().width, evt.height);
                            evt.gc.setBackground(oldBackground);
                        }
                    })
                    .onDispose(tableGray::dispose);

            c.button("add")
                    .text("Add Module")
                    .bottom(10)
                    .right(10)
                    .onSelection(() -> System.out.println(dialog.getSize()));

            c.button("cancel")
                    .text("Cancel")
                    .onSelection(dialog::close)
                    .right(fromLeftOf("add", 10))
                    .bottom(10);
        });
        dialog.setSize(500, 200);
        dialog.addShellListener(new ShellAdapter() {
            @Override
            public void shellClosed(ShellEvent e) {
                dialog.dispose();
            }
        });
        dialog.setVisible(true);
    }

    private CompositeDescription propertiesPageDescription(Shell shell) {
        Color tableGray = new Color(Display.getCurrent(), 245, 245, 245);

        return composite()
                .children(c -> {
                    c.table("list")
                            .left(10)
                            .top(10)
                            // .bottom(fromBottomOf("remove", -7))
                            .height(175)
                            .right(fromLeftOf("remove", 10))
                            .columns("Name", "Version", "Latest Version")
                            .item("Database", "1.0.0", "1.0.0")
                            .item("Email", "1.0.0", "1.0.0")
                            .item("File", "1.0.0", "1.0.0")
                            .item("FTP", "1.0.0", "2.0.0")
                            .item("HTTP", "1.0.0", "1.0.0")
                            .item("Sockets", "1.0.0", "1.0.0")
                            .item("Validation", "1.0.0", "1.0.0")
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
                            .text("Add Module...")
                            .onSelection(() -> {
                                this.showAddModule(shell);
                            })
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
