package swtbuilder;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Label;

public class LabelDescription extends AbstractControlDescription<LabelDescription, Label> {
    private String text;
    private Integer fontSize;
    private Integer fontStyle;

    public LabelDescription() {
        super(Label::new);
    }

    @Override
    protected void setUpControl(Label control, ControlRefs refs) {
        if (text != null) {
            control.setText(text);
        }
        if (fontSize != null || fontStyle != null) {
            Font newFont = createFontFrom(control);
            control.setFont(newFont);
            control.addDisposeListener(e -> newFont.dispose());
        }
    }

    private Font createFontFrom(Label control) {
        FontData[] fontData = control.getFont().getFontData();
        for (FontData data : fontData) {
            if (fontSize != null) {
                data.setHeight(fontSize);
            }
            if (fontStyle != null) {
                data.setStyle(fontStyle);
            }
        }
        Font newFont = new Font(control.getDisplay(), fontData);
        return newFont;
    }

    public LabelDescription text(String text) {
        this.text = text;
        return this;
    }

    public LabelDescription font(int fontSize, int fontStyle) {
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        return this;
    }
}
