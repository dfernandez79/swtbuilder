package swtbuilder.viewer

import org.junit.Test
import swtbuilder.descriptions.CompositeInstanceDescription
import swtbuilder.descriptions.FormAttachmentDescription

import static org.junit.Assert.*

class MockupTest {

    @Test
    void helloWorldLabel() {
        CompositeInstanceDescription composite = Mockup.parse('''
            composite(width: 300, height: 200) {
                label left: 10, top: 10, 'Hello World!'
            }
        ''')

        composite.with {
            assertEquals layoutProperties.width, 300
            assertEquals layoutProperties.height, 200
            assertEquals children.size(), 1
            assertEquals children[0].layoutProperties.left, 10
            assertEquals children[0].layoutProperties.top, 10
            assertEquals children[0].controlProperties.text, 'Hello World!'
        }
    }

    @Test
    void controlRelativeAttachment() {
        CompositeInstanceDescription composite = Mockup.parse('''
            composite(width: 300, height: 200) {
                label id: 'hello', left: 10, top: 10, 'Hello'
                label left: from('hello', 10), top: topOf('hello'), 'World!'
            }
        ''')

        composite.with {
            assertEquals layoutProperties.width, 300
            assertEquals layoutProperties.height, 200
            assertEquals children.size(), 2
            assertEquals children[0].layoutProperties.left, 10
            assertEquals children[0].layoutProperties.top, 10
            assertEquals children[0].controlProperties.text, 'Hello'
            assertTrue children[1].layoutProperties.left instanceof FormAttachmentDescription

            children[1].layoutProperties.with {
                assertEquals left.controlId, 'hello'
                assertEquals left.offset, 10
                assertEquals top.controlId, 'hello'
                assertEquals top.offset, 0
            }
        }
    }

}