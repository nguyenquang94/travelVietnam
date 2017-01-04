
package com.example.kai.travelvietnamapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.kai.travelvietnamapp.data.DatabaseConstants.ADDRESS_EN;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.ADDRESS_VI;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.CATEGORY_ID;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.CATEGORY_TABLE;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.CREATE_AT;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.DESCRIPTION_EN;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.DESCRIPTION_VI;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.DOMINATION_COLOR;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.ENABLE_EN;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.ENABLE_VI;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.ICON;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.ICON_ID;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.ICON_SMALL;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.ICON_SMALL_ID;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.LATITUDE;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.LONGITUDE;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.NAME_EN;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.NAME_IN_URL;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.NAME_PLACE_EN;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.NAME_PLACE_VI;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.NAME_VI;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.O_HEIGHT;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.O_SIZE;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.O_WEIGHT;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.PARENT_ID;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.PLACE_ID;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.SHORT_DESCRIPTION_EN;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.SHORT_DESCRIPTION_VI;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.UPDATE_AT;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants.URL;
import static com.example.kai.travelvietnamapp.data.DatabaseConstants._ID;


public class TravelDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "travel.db";

    public static TravelDbHelper mInstance = null;

    public TravelDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + DatabaseConstants.CATEGORY_TABLE + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                NAME_VI + " TEXT NOT NULL, " +
                NAME_EN + " TEXT NOT NULL, " +
                ICON_ID + " INTEGER, " +
                CREATE_AT + " TEXT, " +
                UPDATE_AT + " TEXT, " +
                ICON_SMALL_ID + " INTEGER, " +
                ICON + " TEXT, " +
                ICON_SMALL + " TEXT" +
                " );";

        final String SQL_CREATE_PLACE_TABLE = "CREATE TABLE " + DatabaseConstants.PLACE_TABLE + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                NAME_PLACE_EN + " TEXT NOT NULL, " +
                NAME_PLACE_VI + " TEXT NOT NULL, " +
                CATEGORY_ID + " INTEGER NOT NULL, " +
                DESCRIPTION_VI + " TEXT, " +
                DESCRIPTION_EN + " TEXT, " +
                SHORT_DESCRIPTION_EN + " TEXT, " +
                SHORT_DESCRIPTION_VI + " TEXT, " +
                ENABLE_VI + " INTEGER," +
                ENABLE_EN + " INTEGER," +
                NAME_IN_URL + " TEXT NOT NULL," +
                ADDRESS_VI + " TEXT NOT NULL," +
                ADDRESS_EN + " TEXT NOT NULL," +
                PARENT_ID + " INTEGER," +
                LATITUDE + " REAL," +
                LONGITUDE + " REAL," +

                " FOREIGN KEY (" + CATEGORY_ID + ") REFERENCES " +
                CATEGORY_TABLE + " (" + DatabaseConstants._ID + "));";

        final String SQL_CREATE_IMAGE_TABLE = "CREATE TABLE " + DatabaseConstants.IMAGE_TABLE + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                URL + " TEXT NOT NULL," +
                CREATE_AT + " TEXT NOT NULL," +
                UPDATE_AT + " TEXT NOT NULL," +
                O_HEIGHT + " INTEGER," +
                O_WEIGHT + " INTEGER," +
                O_SIZE + " INTEGER," +
                DOMINATION_COLOR + " TEXT," +
                PLACE_ID + " INTEGER," +

                " FOREIGN KEY (" + PLACE_ID + ") REFERENCES " +
                PLACE_ID + " (" + DatabaseConstants._ID + "));";

        final String SQL_CREATE_COVER_IMAGE_TABLE = "CREATE TABLE " + DatabaseConstants.CONVE_IMAGE_TABLE + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                URL + " TEXT NOT NULL," +
                CREATE_AT + " TEXT NOT NULL," +
                UPDATE_AT + " TEXT NOT NULL," +
                O_HEIGHT + " INTEGER," +
                O_WEIGHT + " INTEGER," +
                O_SIZE + " INTEGER," +
                DOMINATION_COLOR + " TEXT," +
                PLACE_ID + " INTEGER," +

                " FOREIGN KEY (" + PLACE_ID + ") REFERENCES " +
                PLACE_ID + " (" + DatabaseConstants._ID + "));";

        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORY_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PLACE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_IMAGE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_COVER_IMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.CATEGORY_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.PLACE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.IMAGE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.CONVE_IMAGE_TABLE);
        onCreate(sqLiteDatabase);
    }
    public static TravelDbHelper getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new TravelDbHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }
}
