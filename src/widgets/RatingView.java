package jamaica.android.widgets;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import jamaica.android.builders.TextViewBuilder;
import static jamaica.android.style.get_padding.get_padding;

/**
 * A widget which allows the user to select a rating from 1 to 5 stars. Use
 * get_value() and set_value() to bind the rating value to the widget. If you
 * need to handle user input immediately, you can use 
 * set_onchange_callback(v -> func(v))
 */
public class RatingView extends LinearLayout {
    TextView rating1, rating2, rating3, rating4, rating5;

    /**
     * The current value of the widget.
     */
    int value = 1;
    public RatingView set_value(int value) {
        this.value = value;
        refresh_widget();
        return this;
    }
    public int get_value() {
        return value;
    }


    /**
     * A callback method to handle rating changes.
     */
    private OnRatingChangeCallback onchange_callback;
    public interface OnRatingChangeCallback {
        public void apply(int value);
    }
    public RatingView set_onchange_callback(OnRatingChangeCallback callback) {
        this.onchange_callback = callback;
        return this;
    }


    /**
     * Handle user selection.
     */
    public void select_rating(int value) {
        int old_value = this.value;
        set_value(value);
        if (old_value != value && onchange_callback != null) {
            onchange_callback.apply(value);
        }
    }


    /**
     * Update the widget display.
     */
    private void refresh_widget() {
        rating1.setText("☆");
        rating2.setText("☆");
        rating3.setText("☆");
        rating4.setText("☆");
        rating5.setText("☆");
        if (value > 0) {
            rating1.setText("★");
        }
        if (value > 1) {
            rating2.setText("★");
        }
        if (value > 2) {
            rating3.setText("★");
        }
        if (value > 3) {
            rating4.setText("★");
        }
        if (value > 4) {
            rating5.setText("★");
        }
    }


    /**
     * Construct the widget.
     */
    public RatingView(Context android) {
        super(android);
        int p = get_padding(android);
        rating1 = new TextViewBuilder(android)
                .padding(p, p, p/2, p)
                .onClick(e -> select_rating(1))
                .create();

        rating2 = new TextViewBuilder(android)
                .padding(p, p, p/2, p)
                .onClick(e -> select_rating(2))
                .create();

        rating3 = new TextViewBuilder(android)
                .padding(p, p, p/2, p)
                .onClick(e -> select_rating(3))
                .create();

        rating4 = new TextViewBuilder(android)
                .padding(p, p, p/2, p)
                .onClick(e -> select_rating(4))
                .create();

        rating5 = new TextViewBuilder(android)
                .padding(p, p, p/2, p)
                .onClick(e -> select_rating(5))
                .create();

        setOrientation(LinearLayout.HORIZONTAL);
        addView(rating1);
        addView(rating2);
        addView(rating3);
        addView(rating4);
        addView(rating5);
    }
}
