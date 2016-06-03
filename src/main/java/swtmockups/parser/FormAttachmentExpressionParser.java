package swtmockups.parser;

import swtmockups.model.FormAttachmentExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class FormAttachmentExpressionParser {
    interface PatternMatchExtractor {
        FormAttachmentExpression extractFrom(Matcher matcher);
    }

    enum Format {
        OFFSET("^\\s*(-?\\d+)\\s*$", m -> new FormAttachmentExpression(parseInt(m.group(1)))),
        CONTROL_REF("^\\s*#(\\w+)\\s*$", m -> new FormAttachmentExpression(m.group(1))),
        CONTROL_REF_OFFSET("^\\s*#(\\w+)\\s+(-?\\d+)\\s*$", m -> new FormAttachmentExpression(m.group(1), parseInt(m.group(2)))),
        NUMERATOR("^\\s*(\\d+)%", m -> new FormAttachmentExpression(parseInt(m.group(1)), 0));

        private final Pattern pattern;
        private final PatternMatchExtractor extractor;

        Format(String pattern, PatternMatchExtractor extractor) {
            this.pattern = Pattern.compile(pattern);
            this.extractor = extractor;
        }
    }

    public static FormAttachmentExpression parse(String expression) {
        for (Format format : Format.values()) {
            Matcher matcher = format.pattern.matcher(expression);
            if (matcher.matches()) {
                return format.extractor.extractFrom(matcher);
            }
        }
        // TODO Throw Error
        return null;
    }
}
