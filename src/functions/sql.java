package jamaica.android.functions;

import android.content.*;
import android.database.sqlite.*;
import org.junit.*;
import org.junit.runner.*;
import org.robolectric.*;
import static jamaica.android.functions.testing.*;
import static jamaica.core.functions.lang.*;
import static jamaica.core.functions.testing.*;

@RunWith(RobolectricTestRunner.class)
public class sql {

    // in_transaction 
    @Test public void in_transaction__commits_valid_sql_with_no_exception() {
        final SQLiteDatabase db = create_test_database();
        in_transaction(db, () -> db.execSQL("create table foo (id integer);"));
        assert_that(db.rawQuery("SELECT * from foo;", null) != null);
    }
    @Test public void in_transaction__rolls_back_invalid_sql_and_rethrows_the_exception() {
        final SQLiteDatabase db = create_test_database();
        assert_throws(SQLiteException.class, () -> 
            in_transaction(db, () -> {
                db.execSQL("create table foo (id integer);");
                db.execSQL("fuck!;");
            }));
        assert_throws(SQLiteException.class, () -> db.rawQuery("SELECT * from foo;", null));
    }
    @Test public void in_transaction__handles_nested_transactions_in_a_single_transaction() {
        final SQLiteDatabase db = create_test_database();
        in_transaction(db, () -> in_transaction(db, () -> db.execSQL("create table foo (id integer);")));
        assert_that(db.rawQuery("SELECT * from foo;", null) != null);
    }
    @Test public void in_transaction__rolls_back_invalid_sql_from_nested_transactions() {
        final SQLiteDatabase db = create_test_database();
        assert_throws(SQLiteException.class, () -> 
            in_transaction(db, () -> {
                in_transaction(db, () -> db.execSQL("create table foo (id integer);"));
                db.execSQL("fuck!;");
            }));
        assert_throws(SQLiteException.class, () -> db.rawQuery("SELECT * from foo;", null));
    }
    public static void in_transaction(SQLiteDatabase db, Runnable block) {
        try {
            db.beginTransaction();
            block.run();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
