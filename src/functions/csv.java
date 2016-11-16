package jamaica.android.functions;

import android.content.*;
import jamaica.android.R;
import jamaica.core.exceptions.*;
import jamaica.core.types.*;
import jamaica.core.interfaces.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import static jamaica.android.functions.testing.*;
import static jamaica.core.functions.exceptions.*;
import static java.text.MessageFormat.*;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class csv {

    // format_line_exceptions
    @Test public void format_line_exceptions__creates_a_single_string_summarising_the_errors() {
        ExceptionTuples errors = new ExceptionTuples();
        add_tuple(errors, new NumberFormatException("foo"), 5);
        assertEquals("Line 5: Invalid amount: foo\n", 
                format_line_exceptions(create_test_context(), errors));
    }
    @Test public void format_line_exceptions__accepts_a_function_for_formatting_each_line() {
        ExceptionTuples errors = new ExceptionTuples();
        add_tuple(errors, new NumberFormatException("foo"), 5);
        add_tuple(errors, new NumberFormatException("bar"), 6);
        assertEquals("5foo6bar", 
                format_line_exceptions(create_test_context(), errors, 
                        (context, e, i) -> i + e.getMessage()));
        
    }
    public static String format_line_exceptions(Context context, ExceptionTuples errors) {
        return format_line_exceptions(context, errors, null);
    }
    public static String format_line_exceptions(Context context, ExceptionTuples errors,
            TriFunction<Context, Exception, Integer, String> formatter) {
        final StringBuilder result = new StringBuilder();
        for (Tuple<Exception, Integer> tuple : errors.list) {
            if (formatter != null) {
                result.append(formatter.apply(context, tuple.one, tuple.two));
            } else {
                result.append("Line ").append(tuple.two).append(": ").append(
                        localise_parse_exception(context, tuple.one)).append("\n");
            }
        }
        return result.toString();
    }


    // localise_parse_exception
    @Test public void localise_parse_exception__translates_an_exception_from_strings_xml() {
        assertEquals("Invalid amount: foo", 
            localise_parse_exception(create_test_context(), new NumberFormatException("foo")));
    }
    @Test public void localise_parse_exception__accepts_a_fallback_function_for_unrecognised_exceptions() {
        assertEquals("Where am I?", 
            localise_parse_exception(create_test_context(), new NullPointerException(), e -> "Where am I?"));
    }
    public static String localise_parse_exception(Context context, Exception e) {
        return localise_parse_exception(context, e, null);
    }
    public static String localise_parse_exception(Context context, Exception e, 
            Function<Exception, String> fallback_function) {
        if (e instanceof NumberFormatException) {
            return format(context.getString(R.string.number_format_parse_exception), e.getMessage());
        } else if (e instanceof MissingFieldsException) {
            return format(context.getString(R.string.missing_fields_exception), 
                    ((MissingFieldsException) e).required, ((MissingFieldsException) e).actual);
        } else if (e instanceof UncheckedParseException) {
            return format(context.getString(R.string.parse_exception), e.getMessage());
        } else if (fallback_function != null) {
            return fallback_function.apply(e);
        } else {
            return e.getClass().getName() + ": " + e.getMessage();
        }
    }
}
