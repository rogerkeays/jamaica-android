package jamaica.android.dialogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


/**
 * This class is to reduce the verbosity of building a progress dialog
 * linked to task. You can just subclass it, override doTask().
 */
public class ProgressTask extends AsyncTask<Void, Void, Void> {
    ProgressDialog progress;
    String message = "Loading...";
    Context context;

    public ProgressTask(Context context, String message) {
        this.message = message;
        this.context = context;
    }

    @Override 
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        progress.setMessage(message);
        progress.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... args) {
        doTask();
        return null;
    }
    protected void doTask() {}

    @Override
    protected void onPostExecute(Void result) {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }
} 
