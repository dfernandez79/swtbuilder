package swtbuilder.model

import org.junit.Test

import static org.junit.Assert.*

class FormAttachmentDescriptionTest {

    @Test
    void topOfAttachment() {
        def description = FormAttachmentDescription.topOf('ctrl', 10)

        assertEquals description.controlId, 'ctrl'
        assertEquals description.offset, 10
        assertEquals description.alignment, FormAttachmentDescription.Alignment.TOP
    }

}