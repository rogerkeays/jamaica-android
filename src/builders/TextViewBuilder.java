package jamaica.android.builders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TextView;
import static jamaica.android.functions.ui.*;

public class TextViewBuilder extends ViewBuilder<TextView> {
    public TextViewBuilder(Context context) {
        int p = get_padding(context);
        current = new TextView(context);
        current.setTextSize(get_text_size());
        current.setSingleLine(true);
        current.setPadding(p, p, p, p);
        this.context = context;
    }

    public TextViewBuilder bold() {
        current.setTypeface(null, Typeface.BOLD);
        return this;
    }

    public TextViewBuilder caption() {
        int p = get_padding(context);
        current.setTextSize(get_small_text_size());
        current.setTextColor(Color.GRAY);
        current.setPadding(p, 0, p, p);
        return this;
    }

    public TextViewBuilder color(int color) {
        current.setTextColor(color);
        return this;
    }

    public TextViewBuilder gravity(int gravity) {
        current.setGravity(gravity);
        return this;
    }

    public TextViewBuilder height(int height) {
        current.setHeight(height);
        return this;
    }

    public TextViewBuilder hint(int hint) {
        current.setHint(hint);
        return this;
    }

    public TextViewBuilder hint(String hint) {
        current.setHint(hint);
        return this;
    }

    public TextViewBuilder multiLine() {
        current.setSingleLine(false);
        return this;
    }

    public TextViewBuilder size(float size) {
        current.setTextSize(size);
        return this;
    }

    public TextViewBuilder text(String text) {
        current.setText(text);
        return this;
    }

    public TextViewBuilder text(int text) {
        current.setText(text);
        return this;
    }

    public TextViewBuilder width(int width) {
        current.setWidth(width);
        return this;
    }
}
