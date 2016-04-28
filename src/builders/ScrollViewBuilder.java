package jamaica.android.builders;

import android.content.Context;
import android.view.View;
import android.widget.ScrollView;

public class ScrollViewBuilder extends ViewBuilder<ScrollView> {
    public ScrollViewBuilder(Context context) {
        current = new ScrollView(context);
        current.setFillViewport(true);
    }

    public ScrollViewBuilder add(View view) {
        current.addView(view);
        return this;
    }

    public ScrollViewBuilder fillViewport(boolean fill) {
        current.setFillViewport(fill);
        return this;
    }
}
