package am.narekb.alternativa.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "pastGames";
    private static final String TABLE_GAMES = "games";


    public static final String KEY_ID = "_id";
    public static final String KEY_OUR_SCORE = "our_score";
    public static final String KEY_THEIR_SCORE = "their_score";

    public SQLiteDatabase db;



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

    public void openDB() {
        db = getWritableDatabase();
    }

    //CRUD methods below

    public void addGame(Game game) {
        ContentValues values = new ContentValues();
        values.put(KEY_OUR_SCORE, game.getOurScore());
        values.put(KEY_THEIR_SCORE, game.getTheirScore());

        db.insert(TABLE_GAMES, null, values);
    }

    public Game getGame(int id) {
        //if(db == null) { db = getWritableDatabase();}
        Cursor cursor = db.query(TABLE_GAMES, new String[] {KEY_ID, KEY_OUR_SCORE, KEY_THEIR_SCORE}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Game game = new Game(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)));

        cursor.close();
        return game;
    }

    public Cursor getAllGames() {
        String query = "SELECT * FROM " + TABLE_GAMES;

        //if(db == null) { db = getWritableDatabase();}

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public void deleteGame(int id) {
        //if(db == null) { db = getWritableDatabase();}
        db.delete(TABLE_GAMES, KEY_ID + "=?", new String[] {String.valueOf(id)});
    }
}
