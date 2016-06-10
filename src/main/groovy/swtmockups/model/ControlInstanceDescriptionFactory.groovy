package swtmockups.model

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.ScrolledComposite
import org.eclipse.swt.widgets.*

import java.util.List

@TypeChecked
@CompileStatic
class ControlInstanceDescriptionFactory {

    private List collected

    ControlInstanceDescriptionFactory() {
        this(null)
    }

    private ControlInstanceDescriptionFactory(List<ControlInstanceDescription> collected) {
        this.collected = collected
    }

    private ControlInstanceDescription collect(ControlInstanceDescription description) {
        collected?.add description
        description
    }

    private List<ControlInstanceDescription> collectChildrenWith(@DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {
        def children = []

        closure.delegate = new ControlInstanceDescriptionFactory(children)
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
        children
    }

    CompositeInstanceDescription composite(Class<? extends Composite> controlClass,
                                           Map<String, ?> layoutProperties,
                                           Map<String, ?> controlProperties,
                                           @DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {
        collect(
                new CompositeInstanceDescription(
                        controlClass,
                        layoutProperties,
                        controlProperties,
                        collectChildrenWith(closure))
        ) as CompositeInstanceDescription
    }

    CompositeInstanceDescription composite(Map<String, ?> layoutProperties,
                                           Map<String, ?> controlProperties,
                                           @DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {

        composite Composite, layoutProperties, controlProperties, closure
    }

    // Use of default values ends in ambiguous method signature, that fails with type checking
    CompositeInstanceDescription composite(Map<String, ?> layoutProperties,
                                           @DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {
        composite layoutProperties, [:], closure
    }

    CompositeInstanceDescription composite(@DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {
        composite [:], [:], closure
    }


    CompositeInstanceDescription scrolledComposite(Map<String, ?> layoutProperties,
                                                   Map<String, ?> controlProperties,
                                                   @DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {
        composite ScrolledComposite, layoutProperties, controlProperties, closure
    }

    CompositeInstanceDescription scrolledComposite(Map<String, ?> layoutProperties,
                                                   @DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {
        scrolledComposite layoutProperties, [:], closure
    }

    CompositeInstanceDescription scrolledComposite(@DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {
        scrolledComposite [:], [:], closure
    }

    ControlInstanceDescription label(Map<String, ?> layoutProperties,
                                     Map<String, Object> controlProperties,
                                     String text) {
        controlProperties.text = text
        control Label, layoutProperties, controlProperties
    }

    ControlInstanceDescription label(Map<String, ?> layoutProperties, String text) { label layoutProperties, [:], text }

    ControlInstanceDescription label(String text) { label [:], [:], text }

    ControlInstanceDescription text(Map<String, ?> layoutProperties, Map<String, Object> controlProperties) {
        if (!controlProperties.containsKey('style')) {
            controlProperties.style = SWT.BORDER
        }
        control Text, layoutProperties, controlProperties
    }

    ControlInstanceDescription text(Map<String, ?> layoutProperties) { text layoutProperties, [:] }

    ControlInstanceDescription text() { text [:], [:] }

    ControlInstanceDescription button(Map<String, ?> layoutProperties, String text) {
        control Button, layoutProperties, [text: text]
    }

    ControlInstanceDescription control(Class<? extends Control> controlClass,
                                       Map<String, ?> layoutProperties,
                                       Map<String, ?> controlProperties) {
        collect new GenericControlInstanceDescription(controlClass, layoutProperties, controlProperties)
    }

}
