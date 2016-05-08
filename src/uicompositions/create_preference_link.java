package jamaica.android.uicompositions;

import android.content.Context;
import android.net.Uri;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.content.Intent;
import jamaica.android.widgets.IconPreference;
import jamaica.core.testing.TestGrouper.UILayer;

public class create_preference_link implements UILayer {

    /**
     * Create a PreferenceScreen object that acts as a link with the given
     * title, summary, and icon. The icon may be omitted.
     */
    public static Preference create_preference_link(
                PreferenceScreen root, String url,
                int title_resource, int summary_resource, 
                Integer icon_resource) {

        IconPreference link = new IconPreference(root.getContext());
        link.setIntent(new Intent().setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse(url)));
        link.setTitle(title_resource);
        link.setSummary(summary_resource);
        if (icon_resource != null) {
            link.setIcon(icon_resource);
        }
        return link;
    }
    public static Preference create_preference_link(
                PreferenceScreen root, String url,
                int title_resource, int summary_resource) {

        return create_preference_link(root, url,
                title_resource, summary_resource, null);
    }
}
