package swtbuilder.descriptions

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.eclipse.swt.layout.FormAttachment
import org.eclipse.swt.layout.FormData
import org.eclipse.swt.layout.FormLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

@TypeChecked
@CompileStatic
class CompositeInstanceDescription extends GenericControlInstanceDescription {

    private final List<ControlInstanceDescription> children

    CompositeInstanceDescription(Class<? extends Composite> controlClass,
                                 Map<String, ?> layoutProperties,
                                 Map<String, ?> controlProperties = [:],
                                 List<ControlInstanceDescription> children = []) {
        super(controlClass, layoutProperties, controlProperties)
        this.children = children
    }

    List<ControlInstanceDescription> getChildren() { children }

    @Override
    Composite createInstance(Composite parent) {
        createInstanceReturningReferences(parent).first
    }

    Tuple2<Composite, Map<String, Control>> createInstanceReturningReferences(Composite parent) {
        Composite composite = basicCreateInstance(parent) as Composite
        composite.layout = new FormLayout()

        Map<String, Control> controls = [:]
        children.collect({
            Control control = it.createInstance(composite)

            if (it.layoutProperties.id) {
                controls.put(it.layoutProperties.id as String, control)
            }

            new Tuple2<ControlInstanceDescription, Control>(it, control)
        }).each {
            applyLayout it.first, it.second, controls
        }

        new Tuple2<>(composite, controls)
    }

    private void applyLayout(ControlInstanceDescription description, Control control, Map<String, Control> controls) {
        def layoutProperties = description.layoutProperties
        FormData layoutData = new FormData()
        control.layoutData = layoutData

        addFormAttachment 'top', layoutProperties, layoutData, controls
        addFormAttachment 'right', layoutProperties, layoutData, controls
        addFormAttachment 'bottom', layoutProperties, layoutData, controls
        addFormAttachment 'left', layoutProperties, layoutData, controls
    }

    private void addFormAttachment(String side,
                                   Map<String, ?> layoutProperties,
                                   FormData layoutData,
                                   Map<String, Control> controls) {
        def value = layoutProperties[side]

        switch (value) {
            case Number:
                layoutData[side] = new FormAttachmentDescription(offset: (value as Number).toInteger()) .createAttachment(side, controls)
                break
            case FormAttachment:
                layoutData[side] = value as FormAttachment
                break
            case FormAttachmentDescription:
                layoutData[side] = (value as FormAttachmentDescription).createAttachment(side, controls)
                break
        }
    }

}
