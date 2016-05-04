package jamaica.android.widgets;

import android.content.Context;
import android.widget.LinearLayout;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import android.widget.ImageView;
import static android.widget.LinearLayout.HORIZONTAL;
import jamaica.android.R;
import static jamaica.android.actions.open_url.open_url;
import jamaica.android.builders.ImageViewBuilder;
import jamaica.core.testing.TestGrouper.UILayer;

public class FollowMeView extends LinearLayout implements UILayer {
    
    // references to the components
    ImageView facebook_button, twitter_button, google_plus_button,
            instagram_button, youtube_button;

    // construct the widgets
    public FollowMeView(Context android) {
        super(android);
        setOrientation(HORIZONTAL);
        facebook_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_facebook)
            .visibility(GONE)
            .create();

        twitter_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_twitter)
            .visibility(GONE)
            .create();

        google_plus_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_google_plus)
            .visibility(GONE)
            .create();

        instagram_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_instagram)
            .visibility(GONE)
            .create();

        youtube_button = new ImageViewBuilder(android)
            .image(R.drawable.ic_social_youtube)
            .visibility(GONE)
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
