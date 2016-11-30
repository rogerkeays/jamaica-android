package jamaica.android.functions;

import android.app.*;
import android.content.*;
import android.view.*;
import android.util.*;
import android.widget.*;
import jamaica.android.R;
import jamaica.android.dialogs.*;
import jamaica.core.exceptions.*;
import jamaica.core.types.*;
import jamaica.core.interfaces.*;
import java.io.*;
import org.junit.Test;
import static jamaica.android.functions.testing.*;
import static jamaica.core.functions.csv.*;
import static jamaica.core.functions.exceptions.*;
import static jamaica.core.functions.lang.*;
import static java.text.MessageFormat.*;
import static org.junit.Assert.*;

public class dialogs {

    // show_error_dialog
    public static void show_error_dialog(Activity context, int title_resource, String message) {
        context.runOnUiThread(() -> 
            new AlertDialog.Builder(context)
                    .setTitle(title_resource)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, (dialog, id) -> dialog.cancel())
                    .show());
    }


    // show_import_dialog
    public static boolean show_import_dialog(Activity context, Consumer<File> import_function) {
        return show_import_dialog(context, import_function, null);
    }
    public static boolean show_import_dialog(Activity context, Consumer<File> import_function,
                Function<Throwable, String> exception_localisation_function) {

        new FileChooser(context).setFileListener(file -> {
            new AlertDialog.Builder(context)
                .setTitle(R.string.import_label)
                .setMessage(format(context.getString(R.string.import_confirm), file.getName()))
                .setPositiveButton(android.R.string.ok, (d, id) -> {
                    final Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
                    new ProgressTask(context, context.getString(R.string.import_label)) {
                         @Override protected void doTask() {
                            try {
                                import_function.accept(file);
                                toast.setText(R.string.import_successful);
                                toast.show();
                            } catch (Exception e) {
                                Throwable x = get_root_cause(e);
                                show_error_dialog(context, R.string.import_failed,
                                    x instanceof LineExceptions ? 
                                    format_line_errors((LineExceptions) x, exception_localisation_function) :
                                    localise_exception(x));
                            }
                         }
                    }.execute();
                })
                .setNegativeButton(android.R.string.cancel, (dialog, id) -> dialog.cancel())
                .create()
                .show();
        }).showDialog();
        return true;
    }
}
