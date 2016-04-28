package jamaica.android.builders;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class LinearLayoutBuilder extends ViewBuilder<LinearLayout> {
    public LinearLayoutBuilder(Context context) {
        current = new LinearLayout(context);
        current.setOrientation(LinearLayout.HORIZONTAL);
    }

    public LinearLayoutBuilder add(View view) {
        current.addView(view);
        return this;
    }

    public LinearLayoutBuilder horizontal() {
        current.setOrientation(LinearLayout.HORIZONTAL);
        return this;
    }

    public LinearLayoutBuilder vertical() {
        current.setOrientation(LinearLayout.VERTICAL);
        return this;
    }

    public LinearLayoutBuilder gravity(int gravity) {
        current.setGravity(gravity);
        return this;
    }
}
