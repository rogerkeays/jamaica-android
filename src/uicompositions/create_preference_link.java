package jamaica.android.uicompositions;

import android.content.Context;
import android.net.Uri;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.content.Intent;
import jamaica.core.testing.TestGrouper.UILayer;

public class create_preference_link implements UILayer {

    /**
     * Create a PreferenceScreen object that acts as a link with the given
     * title and summary.
     */
    public static Preference create_preference_link(
                PreferenceScreen root, String url,
                int title_resource, int summary_resource) {
        Preference link = new Preference(root.getContext());
        link.setIntent(new Intent().setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse(url)));
        link.setTitle(title_resource);
        link.setSummary(summary_resource);
        return link;
    }
}
