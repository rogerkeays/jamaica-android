package jamaica.android.functions;

import android.content.Context;
import android.content.Intent;
import static android.content.Intent.ACTION_VIEW;
import static android.net.Uri.parse;

public class ui {

    public static int convert_dips_to_px(Context context, int dips) {
        return (int) (dips * context.getResources().getDisplayMetrics().density);
    }

    public static int get_padding(Context context) { 
        return convert_dips_to_px(context, 12); 
    }

    public static float get_small_text_size() {
        return 18.0f * 0.75f; 
    }
    
    public static float get_text_size() { 
        return 18.0f; 
    }

    public static void open_url(Context android, String url) {
        android.startActivity(new Intent().setAction(ACTION_VIEW).setData(parse(url)));
    }
}
