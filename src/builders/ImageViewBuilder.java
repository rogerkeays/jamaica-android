package jamaica.android.builders;

import android.content.Context;
import android.widget.ImageView;

public class ImageViewBuilder extends ViewBuilder<ImageView> {
    public ImageViewBuilder(Context context) {
        current = new ImageView(context);
    }

    public ImageViewBuilder image(int resource) {
        current.setImageResource(resource);
        return this;
    }
}
