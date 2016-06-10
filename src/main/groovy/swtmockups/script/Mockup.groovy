package swtmockups.script

import org.codehaus.groovy.control.CompilerConfiguration
import org.eclipse.swt.layout.FormAttachment
import swtmockups.model.CompositeInstanceDescription
import swtmockups.model.ControlInstanceDescriptionFactory
import swtmockups.model.FormAttachmentDescription

abstract class Mockup extends Script {

    static CompositeInstanceDescription parse(String script) {
        CompilerConfiguration config = new CompilerConfiguration()
        config.scriptBaseClass = Mockup.name

        GroovyShell shell = new GroovyShell(Mockup.classLoader, config)

        shell.run(script, "MockupScript", []) as CompositeInstanceDescription
    }

    private CompositeInstanceDescription composite

    abstract void scriptBody()

    @Override
    CompositeInstanceDescription run() {
        scriptBody()
        composite
    }

    void composite(Map<String, ?> layoutProperties = [:],
                   Map<String, ?> controlProperties = [:],
                   @DelegatesTo(ControlInstanceDescriptionFactory) Closure closure) {
        composite = new ControlInstanceDescriptionFactory().composite(layoutProperties, controlProperties, closure)
    }


    FormAttachmentDescription from(String controlId, int offset = 0) {
        new FormAttachmentDescription(controlId: controlId, offset: offset)
    }

    FormAttachmentDescription topOf(String controlId, int offset = 0) {
        new FormAttachmentDescription(controlId: controlId, offset: offset)
    }
}
