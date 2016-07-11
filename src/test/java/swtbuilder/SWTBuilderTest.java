package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
        createChildrenOf(shell, c -> c.label().text("Hello World!"));

        assertEquals(1, shell.getChildren().length);
        assertEquals("Hello World!", ((Label) shell.getChildren()[0]).getText());
    }

    @Test
    public void createMultipleLabels() {
        createChildrenOf(shell, c -> {
            c.label().text("Hello World!");
            c.label().text("Other");
        });

        assertEquals(2, shell.getChildren().length);
        assertEquals("Hello World!", ((Label) shell.getChildren()[0]).getText());
        assertEquals("Other", ((Label) shell.getChildren()[1]).getText());
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
        Controls result = createChildrenOf(shell, c -> c.label("test").text("Hello!"));

        assertNotNull(result);
        assertEquals("Hello!", ((Label) result.get("test")).getText());
    }

    @Test
    public void formLayoutIsAppliedByDefault() {
        createChildrenOf(shell, c -> {
        });

        assertTrue(shell.getLayout() instanceof FormLayout);
    }

    @Test
    public void containerSize() {
        createChildrenOf(shell, c -> c.size(300, 200));

        assertEquals(shell.getSize().x, 300);
        assertEquals(shell.getSize().y, 200);
    }

    @Test
    public void topAttachment() {
        Controls children = createChildrenOf(shell, c -> c.label("test").top(10));

        assertTrue(children.get("test").getLayoutData() instanceof FormData);

        FormData data = (FormData) children.get("test").getLayoutData();
        assertEquals(data.top.numerator, 0);
        assertEquals(data.top.offset, 10);
        assertEquals(data.top.denominator, 100);
        assertEquals(data.top.alignment, 0);
        assertNull(data.top.control);
    }

    @Test
    public void leftAttachment() {
        Controls children = createChildrenOf(shell, c -> c.label("test").left(10));

        assertNotNull(children.get("test"));
        assertTrue(children.get("test").getLayoutData() instanceof FormData);

        FormData data = (FormData) children.get("test").getLayoutData();
        assertEquals(data.left.numerator, 0);
        assertEquals(data.left.offset, 10);
        assertEquals(data.left.denominator, 100);
        assertEquals(data.left.alignment, 0);
        assertNull(data.left.control);
    }

    @Test
    public void rightAttachmentUsesNegativeValue() {
        Controls children = createChildrenOf(shell, c -> c.label("test").right(10));

        assertNotNull(children.get("test"));
        assertTrue(children.get("test").getLayoutData() instanceof FormData);

        FormData data = (FormData) children.get("test").getLayoutData();
        assertEquals(data.right.numerator, 0);
        assertEquals(data.right.offset, -10);
        assertEquals(data.right.denominator, 100);
        assertEquals(data.right.alignment, 0);
        assertNull(data.right.control);
    }

    @Test
    public void bottomAttachmentUsesNegativeValue() {
        Controls children = createChildrenOf(shell, c -> c.label("test").bottom(10));

        assertNotNull(children.get("test"));
        assertTrue(children.get("test").getLayoutData() instanceof FormData);

        FormData data = (FormData) children.get("test").getLayoutData();
        assertEquals(data.bottom.numerator, 0);
        assertEquals(data.bottom.offset, -10);
        assertEquals(data.bottom.denominator, 100);
        assertEquals(data.bottom.alignment, 0);
        assertNull(data.bottom.control);
    }

    @Test
    public void attachFromBottomOfAControl() {
        Controls children = createChildrenOf(shell, c -> {
            c.label("test").top(10).left(10);
            c.label("relative").top(fromBottomOf("test", 10));
        });

        assertNotNull(children.get("relative"));
        assertTrue(children.get("relative").getLayoutData() instanceof FormData);

        FormData data = (FormData) children.get("relative").getLayoutData();
        assertEquals(data.top.numerator, 0);
        assertEquals(data.top.offset, 10);
        assertEquals(data.top.denominator, 100);
        assertEquals(data.top.alignment, SWT.BOTTOM);
        assertEquals(data.top.control, children.get("test"));
    }

    @Test
    public void attachFromRightOfAControl() {
        Controls children = createChildrenOf(shell, c -> {
            c.label("test").top(10).left(10);
            c.label("relative").left(fromRightOf("test", 10));
        });

        assertNotNull(children.get("relative"));
        assertTrue(children.get("relative").getLayoutData() instanceof FormData);

        FormData data = (FormData) children.get("relative").getLayoutData();
        assertEquals(data.left.numerator, 0);
        assertEquals(data.left.offset, 10);
        assertEquals(data.left.denominator, 100);
        assertEquals(data.left.alignment, SWT.RIGHT);
        assertEquals(data.left.control, children.get("test"));
    }

    @Test
    public void createButton() {
        createChildrenOf(shell, c -> c.button().text("A Button"));

        assertEquals(1, shell.getChildren().length);
        assertEquals("A Button", ((Button) shell.getChildren()[0]).getText());
    }

    @Test
    public void createButtonWithID() {
        Controls result = createChildrenOf(shell, c -> c.button("btn").text("A Button"));

        assertEquals("A Button", ((Button) result.get("btn")).getText());
    }

    @Test
    public void createButtonWithSelectionListener() {
        Controls result = createChildrenOf(shell, c -> c.button("btn").selection((ref, evt) -> {
        }));

        assertEquals(1, result.get("btn").getListeners(SWT.Selection).length);
        assertTrue(result.get("btn").isListening(SWT.Selection));
    }

    @Test
    public void eventListenerReferencingAnotherControl() {
        final String[] capturedText = new String[1];

        Controls result = createChildrenOf(shell, c -> {
            c.label("label").text("Hello!");
            c.button("btn").selection((ref, evt) -> capturedText[0] = ((Label) ref.get("label")).getText());
        });

        result.get("btn").notifyListeners(SWT.Selection, null);

        assertEquals(capturedText[0], "Hello!");
    }

}
