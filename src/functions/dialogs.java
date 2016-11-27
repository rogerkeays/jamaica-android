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
import static jamaica.core.functions.exceptions.*;
import static jamaica.core.functions.lang.*;
import static java.text.MessageFormat.*;
import static org.junit.Assert.*;

public class dialogs {

    // handle_import_dialog
    public static boolean handle_import_dialog(
                Context context,
                Consumer<File> import_function,
                Function<ExceptionTuples, String> error_formatting_function) {

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
                            } catch (Exception e) {
                                Log.e("handle_import_dialog", "import failed", e);
                                Throwable x = get_root_cause(e);
                                toast.setText(context.getString(R.string.import_error) + 
                                    (x instanceof ExceptionTuples ? 
                                    error_formatting_function.apply((ExceptionTuples) x) : 
                                    localise_exception(x)));
                            }
                            toast.show();
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
