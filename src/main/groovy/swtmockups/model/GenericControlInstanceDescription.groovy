package swtmockups.model

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

@TypeChecked
@CompileStatic
class GenericControlInstanceDescription extends ControlInstanceDescription {

    final Class<? extends Control> controlClass
    final List<String> specialProperties = ['style']

    GenericControlInstanceDescription(Class<? extends Control> controlClass,
                                      Map<String, ?> layoutProperties,
                                      Map<String, ?> controlProperties) {

        super(layoutProperties, controlProperties)
        this.controlClass = controlClass
    }

    @Override
    Control createInstance(Composite parent) {
        basicCreateInstance(parent)
    }

    protected Control basicCreateInstance(Composite parent) {
        Control newControl = controlClass.newInstance([parent, controlStyle] as Object[])
        controlProperties.each {
            if (isWritableProperty(it.key)) {
                newControl[it.key] = it.value
            }
        }
        newControl
    }

    boolean isWritableProperty(String name) {
        !(name in specialProperties)
    }
}
