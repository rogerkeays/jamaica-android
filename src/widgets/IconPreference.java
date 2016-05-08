package jamaica.android.widgets;

import android.content.Context;
import android.preference.Preference;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import jamaica.android.builders.LinearLayoutBuilder;
import jamaica.android.builders.ImageViewBuilder;
import static jamaica.android.style.convert_dips_to_px.convert_dips_to_px;
import static jamaica.android.style.get_padding.get_padding;
import jamaica.core.testing.TestGrouper.UILayer;

/**
 * A Preference widget which supports icon display. This is just for
 * backwards compatability with Android 2.2. Android 3.0 added a
 * setIcon() method to the Preference class;
 */
public class IconPreference extends Preference implements UILayer {

    private Integer icon_resource = null;
    public void setIcon(int icon_resource) {
        this.icon_resource = icon_resource;
    }

    public IconPreference(Context android) {
        super(android);
    }


    /**
     * Wrap the superclass view with a linear layout containing the
     * icon, if it is set.
     */
    @Override
    protected View onCreateView(ViewGroup parent) {
        Context android = parent.getContext();
        if (icon_resource == null) {
            return super.onCreateView(parent);
        } else {
            return new LinearLayoutBuilder(android)
                .horizontal()
                .add(new ImageViewBuilder(android)
                    .image(icon_resource)
                    .padding(get_padding(android))
                    .create())
                .add(super.onCreateView(parent))
                .create();
        }
    }
}

