package jamaica.android.builders;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View.OnClickListener;
import android.widget.Button;
import static jamaica.android.functions.ui.*;

public class ButtonBuilder extends ViewBuilder<Button> {
    public ButtonBuilder(Context context) {
        int p = get_padding(context);
        current = new Button(context);
        current.setPadding(p, p, p, p);
    }

    public ButtonBuilder gravity(int gravity) {
        current.setGravity(gravity);
        return this;
    }

    public ButtonBuilder onClick(OnClickListener listener) {
        current.setOnClickListener(listener);
        return this;
    }

    public ButtonBuilder text(int text) {
        current.setText(text);
        return this;
    }

    public ButtonBuilder text(String text) {
        current.setText(text);
        return this;
    }

    public ButtonBuilder bold() {
        current.setTypeface(null, Typeface.BOLD);
        return this;
    }

    public ButtonBuilder color(int color) {
        current.setTextColor(color);
        return this;
    }
}
