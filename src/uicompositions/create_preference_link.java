package jamaica.android.uicompositions;

import android.content.Context;
import android.net.Uri;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.PreferenceManager;
import android.content.Intent;
import jamaica.core.testing.TestGrouper.UILayer;

public class create_preference_link implements UILayer {

    /**
     * Create a PreferenceScreen object that acts as a link with the given
     * title and summary.
     */
    public static PreferenceScreen create_preference_link(
                PreferenceScreen root, String url,
                int title_resource, int summary_resource) {
        PreferenceScreen link = root.getPreferenceManager()
                .createPreferenceScreen(root.getContext());
        link.setIntent(new Intent().setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse(url)));
        link.setTitle(title_resource);
        link.setSummary(summary_resource);
        return link;
    }
}
