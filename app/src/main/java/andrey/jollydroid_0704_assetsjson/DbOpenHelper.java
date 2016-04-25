package andrey.jollydroid_0704_assetsjson;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by Andrey on 07.04.2016.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    Context mcontext;
    List<Singer> singers;
    SQLiteDatabase db;

    final static String DB_NAME = "Singers.db";
    final static int DB_VERSION = 1;
    public final static String DB_TABLE = "Singers";
    public static final String COLUMN_NAME = "Singer_Name";
    public static final String COLUMN_ID = "Singer_ID";
    public static final String COLUMN_TRACKS = "Singer_Tracks";
    public static final String COLUMN_ALBUMS = "Singer_Albums";
    public static final String COLUMN_LINK = "Singer_Link";
    public static final String COLUMN_GENRES = "Singer_Genres";
    public static final String COLUMN_DESCRIPTION = "Singer_Description";
    public static final String COLUMN_SMALL_IMAGE = "Singer_Small_Image";
    public static final String COLUMN_BIG_IMAGE = "Singer_Big_Image";
    public static final String[] COLUMNS = {COLUMN_NAME, COLUMN_ID, COLUMN_TRACKS, COLUMN_ALBUMS, COLUMN_LINK, COLUMN_GENRES, COLUMN_DESCRIPTION, COLUMN_SMALL_IMAGE, COLUMN_BIG_IMAGE};
    private static String
            DB_CREATE = "CREATE TABLE " + DB_TABLE +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, " + COLUMN_ID +
            " INTEGER, " + COLUMN_TRACKS + " INTEGER, " + COLUMN_ALBUMS
            + " INTEGER, " + COLUMN_LINK + " TEXT, " + COLUMN_GENRES + " TEXT, " + COLUMN_SMALL_IMAGE + " TEXT, " + COLUMN_BIG_IMAGE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT);";

    public DbOpenHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        mcontext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);


    }

    public void InsertDataInDB(List<Singer> singers) {
        if(db==null){
            db=getWritableDatabase();
        }
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        db.execSQL(DB_CREATE);

        for (Singer singer : singers) {
            ContentValues values = new ContentValues(1);
            values.put(COLUMN_NAME, singer.getSinger_name());
            values.put(COLUMN_ID, singer.getId());
            values.put(COLUMN_TRACKS, singer.getTracks());
            values.put(COLUMN_ALBUMS, singer.getAlbum_count());
            values.put(COLUMN_LINK, singer.getLink());
            values.put(COLUMN_GENRES, singer.getGenre().toString());
            values.put(COLUMN_SMALL_IMAGE, singer.getCoverType().getSmall());
            values.put(COLUMN_BIG_IMAGE, singer.getCoverType().getBig());
            values.put(COLUMN_DESCRIPTION, singer.getDescription());


           db.insert(DB_TABLE, null, values);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


