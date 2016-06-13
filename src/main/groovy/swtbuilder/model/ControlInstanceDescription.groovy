package swtbuilder.model

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

@TypeChecked
@CompileStatic
abstract class ControlInstanceDescription {

    private final Map<String, ?> controlProperties
    private final Map<String, ?> layoutProperties

    ControlInstanceDescription(Map<String, ?> layoutProperties, Map<String, ?> controlProperties) {
        this.layoutProperties = layoutProperties
        this.controlProperties = controlProperties
    }

    Map<String, ?> getLayoutProperties() { layoutProperties }

    Map<String, ?> getControlProperties() { controlProperties }

    protected int getControlStyle() {
        (controlProperties.style as Integer) ?: SWT.NONE
    }

    abstract Control createInstance(Composite parent)

}