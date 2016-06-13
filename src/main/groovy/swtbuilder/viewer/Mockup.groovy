package swtbuilder.viewer

import org.codehaus.groovy.control.CompilerConfiguration
import swtbuilder.descriptions.CompositeInstanceDescription
import swtbuilder.SWTBuilder
import swtbuilder.descriptions.FormAttachmentDescription

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
                   @DelegatesTo(SWTBuilder) Closure closure) {
        composite = new SWTBuilder().composite(layoutProperties, controlProperties, closure)
    }


    FormAttachmentDescription from(String controlId, int offset = 0) {
        new FormAttachmentDescription(controlId: controlId, offset: offset)
    }

    FormAttachmentDescription topOf(String controlId, int offset = 0) {
        new FormAttachmentDescription(controlId: controlId, offset: offset)
    }
}
