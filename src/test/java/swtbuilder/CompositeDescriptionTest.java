package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CompositeDescriptionTest {

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
    public void createEmptyComposite() {
        Composite composite = new CompositeDescription().createControl(shell);

        assertNotNull(composite);
        assertEquals(0, composite.getChildren().length);
    }

    @Test
    public void createCompositeWithAControl() {
        Composite composite =
                new CompositeDescription()
                        .chain(d -> d.add("test", new LabelDescription()))
                        .createControl(shell);

        assertEquals(1, composite.getChildren().length);
        assertTrue(composite.getChildren()[0] instanceof Label);
    }

    @Test
    public void createCompositeWithStyle() {
        Composite composite = new CompositeDescription()
                .style(SWT.NO_FOCUS)
                .createControl(shell);

        assertTrue((composite.getStyle() & SWT.NO_FOCUS) == SWT.NO_FOCUS);
    }

    @Test
    public void usesFormLayoutByDefault() {
        Composite composite = new CompositeDescription().createControl(shell);

        assertTrue(composite.getLayout() instanceof FormLayout);
    }

    @Test
    public void applyLayoutToChild() {
        Map<String, Control> refs = new HashMap<>();

        new CompositeDescription()
                .chain(d -> d.label("test").left(5).top(5))
                .createControl(shell, refs);

        assertTrue(refs.get("test").getLayoutData() instanceof FormData);

        FormData formData = (FormData) refs.get("test").getLayoutData();
        assertEquals(5, formData.left.offset);
        assertEquals(5, formData.top.offset);
    }

}