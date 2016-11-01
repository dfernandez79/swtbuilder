package swtbuilder.examples;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import static swtbuilder.SWTBuilder.createChildren;
import static swtbuilder.SWTBuilder.fillLayout;

public class HelloWorld extends Example {
    public static void main(String... args) {
        new HelloWorld().run();
    }

    @Override
    protected Shell create() {
        Shell shell = new Shell();
        shell.setSize(300, 40);

        createChildren(shell, fillLayout(),
                       c -> c.label("lbl").text("Hello World!").style(SWT.CENTER).background(SWT.COLOR_CYAN));
        return shell;
    }
}
