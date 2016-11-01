package swtbuilder.examples;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class Example {
    protected void run() {
        System.setProperty("org.eclipse.swt.internal.carbon.smallFonts", "");
        Shell shell = create();
        shell.open();
        waitUntilDisposed(shell);
    }

    protected abstract Shell create();

    private void waitUntilDisposed(Shell shell) {
        Display display = Display.getDefault();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
