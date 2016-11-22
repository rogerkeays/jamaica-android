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
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import static jamaica.android.functions.testing.*;
import static jamaica.core.functions.exceptions.*;
import static jamaica.core.functions.lang.*;
import static java.text.MessageFormat.*;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class csv {

    // default_format_parse_errors
    @Test public void default_format_parse_errors__creates_a_single_string_summarising_the_errors() {
        ExceptionTuples errors = new ExceptionTuples();
        add_tuple(errors, new NumberFormatException("foo"), 5);
        assertEquals("Line 5: Invalid amount: foo\n", 
                default_format_parse_errors(create_test_context(), errors));
    }
    @Test public void default_format_parse_errors__accepts_a_function_for_localising_each_line() {
        ExceptionTuples errors = new ExceptionTuples();
        add_tuple(errors, new NumberFormatException("foo"), 5);
        add_tuple(errors, new NumberFormatException("bar"), 6);
        assertEquals("Line 5: FOO\nLine 6: BAR\n", 
                default_format_parse_errors(create_test_context(), errors, 
                        (context, e) -> e.getMessage().toUpperCase()));
        
    }
    public static String default_format_parse_errors(Context context, ExceptionTuples errors) {
        return default_format_parse_errors(context, errors, null);
    }
    public static String default_format_parse_errors(Context context, ExceptionTuples errors,
            BiFunction<Context, Throwable, String> localisation_function) {
        final StringBuilder result = new StringBuilder();
        for (Tuple<Exception, Integer> tuple : errors.list) {
            result.append(format(context.getString(R.string.line_number), tuple.two))
                .append(localisation_function == null ? 
                    default_localise_parse_exception(context, tuple.one) :
                    localisation_function.apply(context, tuple.one))
                .append("\n");
        }
        return result.toString();
    }


    // default_localise_parse_exception
    @Test public void default_localise_parse_exception__translates_an_exception_from_strings_xml() {
        assertEquals("Invalid amount: foo", 
            default_localise_parse_exception(create_test_context(), new NumberFormatException("foo")));
    }
    @Test public void default_localise_parse_exception__accepts_a_fallback_function_for_unrecognised_exceptions() {
        assertEquals("Where am I?", 
            default_localise_parse_exception(create_test_context(), new NullPointerException(), e -> "Where am I?"));
    }
    @Test public void default_localise_parse_exception__formats_the_exception_if_the_fallback_function_returns_null() {
        assertEquals("java.lang.NullPointerException: null", 
            default_localise_parse_exception(create_test_context(), new NullPointerException(), e -> null));
    }
    public static String default_localise_parse_exception(Context context, Throwable e) {
        return default_localise_parse_exception(context, e, null);
    }
    public static String default_localise_parse_exception(Context context, Throwable e, 
            Function<Throwable, String> fallback_function) {
        if (e instanceof NumberFormatException) {
            return format(context.getString(R.string.number_format_parse_exception), e.getMessage());
        } else if (e instanceof MissingFieldsException) {
            return format(context.getString(R.string.missing_fields_exception), 
                    ((MissingFieldsException) e).required, ((MissingFieldsException) e).actual);
        } else if (e instanceof UncheckedParseException) {
            return format(context.getString(R.string.parse_exception), e.getMessage());
        } else if (fallback_function != null) {
            return coalesce(fallback_function.apply(e), e.getClass().getName() + ": " + e.getMessage());
        } else {
            return e.getClass().getName() + ": " + e.getMessage();
        }
    }


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
                            } catch (Exception e) {
                                Log.e("handle_import_dialog", "import failed", e);
                                Throwable x = get_root_cause(e);
                                toast.setText(context.getString(R.string.import_error) + 
                                    (x instanceof ExceptionTuples ? 
                                    error_formatting_function.apply((ExceptionTuples) x) : 
                                    default_localise_parse_exception(context, x)));
                                toast.show();
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
