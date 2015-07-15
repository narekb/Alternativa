package am.narekb.alternativa.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "pastGames";
    private static final String TABLE_GAMES = "games";


    private static final String KEY_ID = "id";
    private static final String KEY_OUR_SCORE = "our_score";
    private static final String KEY_THEIR_SCORE = "their_score";


    public DBHandler (Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_GAMES + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_OUR_SCORE + " INTEGER, "
                + KEY_THEIR_SCORE + " INTEGER " + ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
        onCreate(db);
    }

    //TODO: Add CRUD methods
}
