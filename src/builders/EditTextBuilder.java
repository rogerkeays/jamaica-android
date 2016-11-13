package jamaica.android.builders;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.EditText;
import static jamaica.android.functions.ui.*;

public class EditTextBuilder extends ViewBuilder<EditText> {
    public EditTextBuilder(Context context) {
        int p = get_padding(context);
        current = new EditText(context);
        current.setTextSize(get_text_size());
        current.setSingleLine(true);
        current.setGravity(Gravity.TOP);
        current.setPadding(p, p, p, p);
    }

    public EditTextBuilder bold() {
        current.setTypeface(null, Typeface.BOLD);
        return this;
    }

    public EditTextBuilder color(int color) {
        current.setTextColor(color);
        return this;
    }

    public EditTextBuilder gravity(int gravity) {
        current.setGravity(gravity);
        return this;
    }

    public EditTextBuilder height(int height) {
        current.setHeight(height);
        return this;
    }

    public EditTextBuilder hint(int hint) {
        current.setHint(hint);
        return this;
    }

    public EditTextBuilder hint(String hint) {
        current.setHint(hint);
        return this;
    }

    public EditTextBuilder inputType(int inputType) {
        current.setInputType(inputType);
        return this;
    }

    public EditTextBuilder multiLine() {
        current.setSingleLine(false);
        current.setGravity(Gravity.TOP);
        return this;
    }

    public EditTextBuilder size(float size) {
        current.setTextSize(size);
        return this;
    }

    public EditTextBuilder text(String text) {
        current.setText(text);
        return this;
    }

    public EditTextBuilder width(int width) {
        current.setWidth(width);
        return this;
    }
}
