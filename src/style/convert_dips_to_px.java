package jamaica.android.style;

import android.content.Context;
import jamaica.core.testing.TestGrouper.UILayer;

public class convert_dips_to_px implements UILayer {

    /**
     * Convert a density-independent-pixels value to real pixels.
     */
    public static int convert_dips_to_px(Context context, int dips) {
        return (int) (dips * context.getResources().getDisplayMetrics().density);
    }
}
