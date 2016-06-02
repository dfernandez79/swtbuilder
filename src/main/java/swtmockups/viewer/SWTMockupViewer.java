package swtmockups.viewer;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swtmockups.model.Mockup;
import swtmockups.parser.MockupParseException;
import swtmockups.parser.MockupXmlParser;

public class SWTMockupViewer {
    public static void main(String[] args) {
        System.setProperty("org.eclipse.swt.internal.carbon.smallFonts", "");
        try {
            SWTMockupViewer window = new SWTMockupViewer();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() throws MockupParseException {
        Display display = Display.getDefault();
        Shell shell = createShell();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    private Shell createShell() throws MockupParseException {
        Shell shell = new Shell();
        shell.setSize(450, 300);
        shell.setText("SWT Mockup Viewer");

        Mockup mockup = new MockupXmlParser().parse(getClass().getResourceAsStream("test.xml"));
        mockup.instantiate(shell);

        return shell;
    }
}
