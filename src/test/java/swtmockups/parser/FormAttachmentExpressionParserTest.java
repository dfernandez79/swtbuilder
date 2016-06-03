package swtmockups.parser;

import org.junit.Test;
import swtmockups.model.FormAttachmentExpression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FormAttachmentExpressionParserTest {
    @Test
    public void offsetOnly() {
        FormAttachmentExpression expression = FormAttachmentExpressionParser.parse("123");
        assertTrue(expression.offset.isPresent());
        assertEquals(expression.offset.get(), Integer.valueOf(123));
    }

    @Test
    public void negativeOffset() {
        FormAttachmentExpression expression = FormAttachmentExpressionParser.parse("-123");
        assertTrue(expression.offset.isPresent());
        assertEquals(expression.offset.get(), Integer.valueOf(-123));
    }

    @Test
    public void controlAndOffset() {
        FormAttachmentExpression expression = FormAttachmentExpressionParser.parse("#control 123");
        assertTrue(expression.offset.isPresent());
        assertTrue(expression.controlId.isPresent());
        assertEquals(expression.controlId.get(), "control");
        assertEquals(expression.offset.get(), Integer.valueOf(123));
    }

    @Test
    public void percentageOnly() {
        FormAttachmentExpression expression = FormAttachmentExpressionParser.parse("50%");
        assertTrue(expression.numerator.isPresent());
        assertEquals(expression.numerator.get(), Integer.valueOf(50));
    }
}