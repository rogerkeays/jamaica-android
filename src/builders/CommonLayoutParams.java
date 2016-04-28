package jamaica.android.builders;

import android.view.ViewGroup.LayoutParams;
import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.widget.LinearLayout;

public class CommonLayoutParams {

    public static LayoutParams FILL_X_FILL_Y = 
            new LayoutParams(FILL_PARENT, FILL_PARENT);

    public static LinearLayout.LayoutParams FILL_X_EXPAND_Y = 
            new LinearLayout.LayoutParams(FILL_PARENT, 0, 1f);

    public static LayoutParams FILL_X_WRAP_Y = 
            new LayoutParams(FILL_PARENT, WRAP_CONTENT);

    public static LinearLayout.LayoutParams EXPAND_X_FILL_Y = 
            new LinearLayout.LayoutParams(0, FILL_PARENT, 1f);

    public static LinearLayout.LayoutParams EXPAND_X_EXPAND_Y = 
            new LinearLayout.LayoutParams(0, 0, 1f);

    public static LinearLayout.LayoutParams EXPAND_X_WRAP_Y = 
            new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f);

    public static LayoutParams WRAP_X_FILL_Y = 
            new LayoutParams(WRAP_CONTENT, FILL_PARENT);

    public static LinearLayout.LayoutParams WRAP_X_EXPAND_Y = 
            new LinearLayout.LayoutParams(WRAP_CONTENT, 0, 1f);

    public static LayoutParams WRAP_X_WRAP_Y = 
            new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

}
