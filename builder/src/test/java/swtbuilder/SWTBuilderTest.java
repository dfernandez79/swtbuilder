package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static swtbuilder.SWTBuilder.*;

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
        createChildren(shell, c -> c.label().text("Hello World!"));

        assertEquals(1, shell.getChildren().length);
        assertEquals("Hello World!", ((Label) shell.getChildren()[0]).getText());
    }

    @Test
    public void createLabelWithStyle() {
        createChildren(shell, c -> {
            c.label().text("Hello World!").style(SWT.RIGHT);
            c.label().text("Other");
        });

        assertEquals(2, shell.getChildren().length);
        assertTrue((SWT.RIGHT & shell.getChildren()[0].getStyle()) == SWT.RIGHT);
    }

    @Test
    public void createLabelWithId() {
        ControlRefs result = createChildren(shell, c -> c.label("test").text("Hello!"));

        assertNotNull(result);
        assertEquals("Hello!", result.label("test").getText());
    }

    @Test
    public void createButton() {
        createChildren(shell, c -> c.button().text("A Button"));

        assertEquals(1, shell.getChildren().length);
        assertEquals("A Button", ((Button) shell.getChildren()[0]).getText());
    }

    @Test
    public void createButtonWithSelectionListener() {
        ControlRefs result = createChildren(shell, c -> c.button("btn").onSelection(() -> {
        }));

        assertEquals(1, result.get("btn").getListeners(SWT.Selection).length);
        assertTrue(result.get("btn").isListening(SWT.Selection));
    }

    @Test
    public void eventListenerReferencingItself() {
        final String[] capturedText = new String[1];

        ControlRefs result = createChildren(shell, c -> {
            c.button("btn")
                    .text("Button")
                    .onSelection((evt, btn) -> capturedText[0] = btn.getText());
        });

        result.get("btn").notifyListeners(SWT.Selection, null);

        assertEquals(capturedText[0], "Button");
    }

    @Test
    public void eventListenerReferencingAnotherControl() {
        final String[] capturedText = new String[1];

        ControlRefs result = createChildren(shell, c -> {
            c.label("label").text("Hello!");
            c.button("btn").onSelection((evt, btn, refs) -> capturedText[0] = refs.label("label").getText());
        });

        result.get("btn").notifyListeners(SWT.Selection, null);

        assertEquals(capturedText[0], "Hello!");
    }

    @Test
    public void eventListenerWithEventReferenceOnly() {
        final String[] capturedText = new String[1];

        ControlRefs result = createChildren(shell, c -> {
            c.label("label").text("Hello!");
            c.button("btn").onSelection((evt) -> capturedText[0] = evt.text);
        });

        Event eventData = new Event();
        eventData.text = "Test";
        result.get("btn").notifyListeners(SWT.Selection, eventData);

        assertEquals(capturedText[0], "Test");
    }

    @Test
    public void createReadOnlyCombo() {
        ControlRefs result = createChildren(shell, c -> c.dropdown("cbo").items("Option 1", "Option 2"));

        Combo created = result.combo("cbo");
        assertEquals(2, created.getItemCount());
        assertEquals(created.getItem(0), "Option 1");
        assertEquals(created.getItem(1), "Option 2");
    }

    @Test
    public void createComposite() {
        Composite result = composite()
                .children(c -> c.label().text("Hello"))
                .createControl(shell);

        assertNotNull(result);
        assertEquals(1, result.getChildren().length);
        assertTrue(result.getChildren()[0] instanceof Label);
        assertEquals("Hello", ((Label) result.getChildren()[0]).getText());
    }

    @Test
    public void genericControlUsage() {
        Composite result = composite()
                .children(c -> c.control(Label::new).setUp(label -> label.setText("Testing")))
                .createControl(shell);

        assertNotNull(result);
        assertEquals(1, result.getChildren().length);
        assertTrue(result.getChildren()[0] instanceof Label);
        assertEquals("Testing", ((Label) result.getChildren()[0]).getText());
    }

    @Test
    public void createCheckbox() {
        Button created =
                (Button) createChildren(shell, c -> c.checkbox("test").text("A checkbox").selected()).get("test");

        assertTrue((created.getStyle() & SWT.CHECK) == SWT.CHECK);
        assertTrue(created.getSelection());
    }

    @Test
    public void createUnselectedCheckbox() {
        Button created = (Button) createChildren(shell, c -> c.checkbox("test").text("A checkbox").selected(false))
                .get("test");

        assertTrue((created.getStyle() & SWT.CHECK) == SWT.CHECK);
        assertFalse(created.getSelection());
    }

    @Test
    public void createLink() {
        Link created = (Link) createChildren(shell, c -> c.link("test").text("This is a <a>link</a>")).get("test");

        assertEquals("This is a <a>link</a>", created.getText());
    }

    @Test
    public void createEmptyGroup() {
        Group group = createChildren(shell, c -> c.group("test").text("A group")).group("test");

        assertEquals(0, group.getChildren().length);
        assertEquals("A group", group.getText());
    }

    @Test
    public void createTextInput() {
        Text text = createChildren(shell, c -> c.textInput("test").text("Hello")).textInput("test");

        assertTrue((text.getStyle() & SWT.BORDER) == SWT.BORDER);
        assertEquals("Hello", text.getText());
    }

    @Test
    public void createUsingFillLayout() {
        shell.setSize(300, 200);
        Label label = createChildren(shell, fillLayout(), c -> c.label("lbl").text("Hello")).label("lbl");

        shell.layout();

        assertEquals(shell.getClientArea().width, label.getSize().x);
        assertEquals(shell.getClientArea().height, label.getSize().y);
    }
}
