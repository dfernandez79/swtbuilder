package swtmockups.model

import groovy.transform.TypeChecked
import org.eclipse.swt.widgets.Text
import org.junit.Test

import static org.junit.Assert.*

@TypeChecked
class ControlInstanceDescriptionFactoryTest {

    private ControlInstanceDescriptionFactory factory = new ControlInstanceDescriptionFactory()

    @Test
    void compositeWithoutChildren() {
        def created = factory.composite(width: 100, height: 100) {}

        created.with {
            assertEquals children.size(), 0
            assertEquals layoutProperties.width, 100
            assertEquals layoutProperties.height, 100
        }
    }

    @Test
    void compositeWithChildren() {
        def created = factory.composite(width: 100, height: 100) {
            label 'Hello world!'
        }

        created.with {
            assertEquals children.size(), 1
            assertEquals children[0].controlProperties.text, 'Hello world!'
            assertEquals layoutProperties.width, 100
            assertEquals layoutProperties.height, 100
        }
    }

    @Test
    void text() {
        GenericControlInstanceDescription created = factory.text() as GenericControlInstanceDescription

        created.with {
            assertEquals created.controlClass, Text
        }
    }

}