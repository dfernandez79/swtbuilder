package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static swtbuilder.SWTBuilder.createChildrenOf;

public class SWTBuilderTest {

    private Shell shell;

    @Before
    public void createShell() {
        shell = new Shell();
    }

    @After
    public void disposeShell() {
        shell.dispose();
    }

    @Test
    public void createLabel() {
        createChildrenOf(shell, c -> c.label().text("Hello World!"));

        assertEquals(1, shell.getChildren().length);
        assertEquals("Hello World!", ((Label) shell.getChildren()[0]).getText());
    }

    @Test
    public void createLabelWithStyle() {
        createChildrenOf(shell, c -> {
            c.label().text("Hello World!").style(SWT.RIGHT);
            c.label().text("Other");
        });

        assertEquals(2, shell.getChildren().length);
        assertTrue((SWT.RIGHT & shell.getChildren()[0].getStyle()) == SWT.RIGHT);
    }

    @Test
    public void createLabelWithId() {
        ControlRefs result = createChildrenOf(shell, c -> c.label("test").text("Hello!"));

        assertNotNull(result);
        assertEquals("Hello!", result.label("test").getText());
    }

    @Test
    public void containerSize() {
        createChildrenOf(shell, c -> c.size(300, 200));

        assertEquals(shell.getSize().x, 300);
        assertEquals(shell.getSize().y, 200);
    }

    @Test
    public void createButton() {
        createChildrenOf(shell, c -> c.button().text("A Button"));

        assertEquals(1, shell.getChildren().length);
        assertEquals("A Button", ((Button) shell.getChildren()[0]).getText());
    }

    @Test
    public void createButtonWithSelectionListener() {
        ControlRefs result = createChildrenOf(shell, c -> c.button("btn").onSelection((refs, evt) -> {
        }));

        assertEquals(1, result.get("btn").getListeners(SWT.Selection).length);
        assertTrue(result.get("btn").isListening(SWT.Selection));
    }

    @Test
    public void eventListenerReferencingAnotherControl() {
        final String[] capturedText = new String[1];

        ControlRefs result = createChildrenOf(shell, c -> {
            c.label("label").text("Hello!");
            c.button("btn").onSelection((refs, evt) -> capturedText[0] = refs.label("label").getText());
        });

        result.get("btn").notifyListeners(SWT.Selection, null);

        assertEquals(capturedText[0], "Hello!");
    }

    @Test
    public void eventListenerReferencingAnotherControlWithoutEventParam() {
        final String[] capturedText = new String[1];

        ControlRefs result = createChildrenOf(shell, c -> {
            c.label("label").text("Hello!");
            c.button("btn").onSelection(refs -> capturedText[0] = refs.label("label").getText());
        });

        result.get("btn").notifyListeners(SWT.Selection, null);

        assertEquals(capturedText[0], "Hello!");
    }

    @Test
    public void createReadOnlyCombo() {
        ControlRefs result = createChildrenOf(shell, c -> c.dropdown("cbo").items("Option 1", "Option 2"));

        Combo created = result.combo("cbo");
        assertEquals(2, created.getItemCount());
        assertEquals(created.getItem(0), "Option 1");
        assertEquals(created.getItem(1), "Option 2");
    }

}
