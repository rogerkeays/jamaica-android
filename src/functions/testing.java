package jamaica.android.functions;

import android.content.*;
import android.database.sqlite.*;
import org.junit.*;
import org.junit.runner.*;
import org.robolectric.*;
import static jamaica.core.functions.testing.*;

@RunWith(RobolectricTestRunner.class)
public class testing {
    
    // create_test_context
    public static Context create_test_context() {
        return Robolectric.application.getApplicationContext();
    }

    // create_test_database
    @Test public void create_test_database__returns_an_open_sqlite_database() {
        assert_true(create_test_database().isOpen());
    }
    @Test public void create_test_database__can_be_called_multiple_times() {
        assert_true(!create_test_database().getPath()
                .equals(create_test_database().getPath()));
    }
    public static SQLiteDatabase create_test_database() {
        return create_test_database(create_test_context());
    }
    public static SQLiteDatabase create_test_database(Context android) {
        return android.openOrCreateDatabase(create_random_string(), 0, null);
    }
}
