package swtbuilder;

import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

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
}
