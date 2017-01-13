package swtbuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControlRefsTest {
    @Test(expected = UnknownControlException.class)
    public void throwsExceptionForUnknownControls() {
        ControlRefs refs = new ControlRefs();
        refs.get("test");
    }

    @Test(expected = DuplicateIdException.class)
    public void throwsExceptionWhenAnIdIsUsedMultipleTimes() {
        Shell shell = new Shell();
        ControlRefs refs = new ControlRefs();

        try {
            refs.add("test", shell);
            refs.add("test", shell);
        } finally {
            shell.dispose();
        }
    }

    @Test
    public void providesMethodsForCommonControlTypes() {
        Shell shell = new Shell();
        ControlRefs refs = new ControlRefs();

        Button button = new Button(shell, SWT.NONE);
        Label label = new Label(shell, SWT.NONE);
        Combo combo = new Combo(shell, SWT.NONE);
        Text text = new Text(shell, SWT.NONE);
        Group group = new Group(shell, SWT.NONE);

        refs.add("button", button);
        refs.add("label", label);
        refs.add("combo", combo);
        refs.add("text", text);
        refs.add("group", group);

        assertEquals(refs.button("button"), button);
        assertEquals(refs.label("label"), label);
        assertEquals(refs.combo("combo"), combo);
        assertEquals(refs.textInput("text"), text);
        assertEquals(refs.group("group"), group);
    }
}
