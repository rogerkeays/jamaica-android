package jamaica.android.widgets;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ImageView;
import jamaica.android.R;
import jamaica.android.builders.ImageViewBuilder;
import jamaica.core.testing.TestGrouper.UILayer;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.LinearLayout.HORIZONTAL;
import static jamaica.android.functions.ui.*;

public class FollowMeView extends LinearLayout implements UILayer {
    
    // references to the components
    ImageView facebook_button, twitter_button, google_plus_button,
            instagram_button, youtube_button;

    // construct the widgets
    public FollowMeView(Context android) {
        super(android);
        setOrientation(HORIZONTAL);
        int p = get_padding(android);
        facebook_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_facebook)
            .visibility(GONE)
            .padding(p, p, 0, p)
            .create();

        twitter_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_twitter)
            .visibility(GONE)
            .padding(p, p, 0, p)
            .create();

        google_plus_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_google_plus)
            .visibility(GONE)
            .padding(p, p, 0, p)
            .create();

        instagram_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_instagram)
            .visibility(GONE)
            .padding(p, p, 0, p)
            .create();

        youtube_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_youtube)
            .visibility(GONE)
            .padding(p, p, 0, p)
            .create();

        addView(facebook_button);
        addView(twitter_button);
        addView(google_plus_button);
        addView(instagram_button);
        addView(youtube_button);
    }

    // configure the target urls
    public FollowMeView setFacebookURL(String url) {
        facebook_button.setOnClickListener(v -> open_url(getContext(), url));
        facebook_button.setVisibility(VISIBLE);
        return this;
    }
    public FollowMeView setTwitterURL(String url) {
        twitter_button.setOnClickListener(v -> open_url(getContext(), url));
        twitter_button.setVisibility(VISIBLE);
        return this;
    }
    public FollowMeView setGooglePlusURL(String url) {
        google_plus_button.setOnClickListener(v -> open_url(getContext(), url));
        google_plus_button.setVisibility(VISIBLE);
        return this;
    }
    public FollowMeView setInstagramURL(String url) {
        instagram_button.setOnClickListener(v -> open_url(getContext(), url));
        instagram_button.setVisibility(VISIBLE);
        return this;
    }
    public FollowMeView setYouTubeURL(String url) {
        youtube_button.setOnClickListener(v -> open_url(getContext(), url));
        youtube_button.setVisibility(VISIBLE);
        return this;
    }
}
