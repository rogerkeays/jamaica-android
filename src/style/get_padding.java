package jamaica.android.style;

import android.content.Context;
import static jamaica.android.style.convert_dips_to_px.convert_dips_to_px;
import jamaica.core.testing.TestGrouper.UILayer;

public class get_padding implements UILayer {


    /**
     * Get the standard padding in px for the current device.
     */
    public static int get_padding(Context context) { 
        return convert_dips_to_px(context, 12); 
    }
}
