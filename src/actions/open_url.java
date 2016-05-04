package jamaica.android.actions;

import android.content.Context;
import android.content.Intent;
import static android.content.Intent.ACTION_VIEW;
import static android.net.Uri.parse;
import jamaica.core.testing.TestGrouper.UILayer;

public class open_url implements UILayer {

    /**
     * Opens the given url using the Android Intent system.
     */
    public static void open_url(Context android, String url) {
        android.startActivity(
                new Intent().setAction(ACTION_VIEW).setData(parse(url)));
    }
}
