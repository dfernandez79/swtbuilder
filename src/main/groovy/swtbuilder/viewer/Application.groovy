package swtbuilder.viewer

import groovy.transform.TypeChecked
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import swtbuilder.SWTBuilder

import static swtbuilder.descriptions.Colors.darkGray
import static swtbuilder.descriptions.FormAttachmentDescription.from

@TypeChecked
class Application {

    static void main(String[] args) {
        System.setProperty("org.eclipse.swt.internal.carbon.smallFonts", "");
        try {
            Application window = new Application();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void open() {
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

    private Shell createShell() {
        Shell shell = new Shell()
        shell.setSize(450, 300)
        shell.text = "SWT Mockup Viewer"
        shell.layout = new FillLayout()

        SWTBuilder factory = new SWTBuilder()

        Map<String, Control> controls = factory.composite {

            label id: 'label', left: 10, top: 10, 'Mockup File'
            text id: 'fileName', left: from('label', 10), top: 10, right: from('open', 10)
            button id: 'open', right: 10, top: 10, 'Open'
            scrolledComposite id: 'contents', top: from('fileName', 10), left: 0, right: 0, bottom: 0, [background: darkGray()], {
            }

        }.createInstanceReturningReferences(shell).second

        shell;
    }

////        FileSystem fs
////        fs = FileSystems.default
////        Path tmpDir
////        tmpDir = fs.getPath('Users', 'diegofernandez', 'prueba')
////        GroovyScriptEngine scriptEngine = new GroovyScriptEngine([tmpDir.toUri().toURL()] as URL)
////        WatchService watchService = fs.newWatchService()
////        watchService.withCloseable {
////            watchKey = tmpDir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY)
////            while (true) {}
////        }

}
