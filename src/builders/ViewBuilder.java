package jamaica.android.builders;

import android.content.Context;
import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.GRAY;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;

public abstract class ViewBuilder<T extends View> {

    Context context;

    /**
     * The view being built. This must be created in the subclass constructor.
     */
    protected T current;


    /**
     * Get the built view.
     */
    public T create() {
        return current;
    }

    public ViewBuilder<T> backgroundAlpha(int percent) {
        current.getBackground().setAlpha(255 * percent / 100);
        return this;
    }

    public ViewBuilder<T> backgroundColor(int color) {
        current.setBackgroundColor(color);
        return this;
    }

    public ViewBuilder<T> focusable(boolean focusable) {
        current.setFocusable(focusable);
        return this;
    }

    public ViewBuilder<T> id(int color) {
        current.setId(color);
        return this;
    }

    public ViewBuilder<T> layout(LayoutParams layout) {
        current.setLayoutParams(layout);
        return this;
    }

    public ViewBuilder<T> lowlight() {
        backgroundColor(GRAY);
        backgroundAlpha(50);
        return this;
    }

    public ViewBuilder<T> onClick(OnClickListener listener) {
        current.setOnClickListener(listener);
        return this;
    }

    public ViewBuilder<T> onLongClick(OnLongClickListener listener) {
        current.setOnLongClickListener(listener);
        return this;
    }

    public ViewBuilder<T> padding(int p) {
        current.setPadding(p, p, p, p);
        return this;
    }

    public ViewBuilder<T> padding(int l, int t, int r, int b) {
        current.setPadding(l, t, r, b);
        return this;
    }

    public ViewBuilder<T> tag(Object tag) {
        current.setTag(tag);
        return this;
    }

    public ViewBuilder<T> visibility(int visibility) {
        current.setVisibility(visibility);
        return this;
    }
}
