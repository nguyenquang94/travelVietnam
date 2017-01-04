package com.example.kai.travelvietnamapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Kai on 01/03/2016.
 */

public class TravelProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private TravelDbHelper travelDbHelper;

    private SQLiteDatabase db;

    static final int CATEGORY = 201704011;
    static final int PLACE = 201704012;
    static final int PLACE_WITH_CATEGORY = 201704013;
    static final int IMAGE = 201704014;
    static final int COVER_IMAGE = 201705031;

    private static final SQLiteQueryBuilder sPlaceAndCategoryBuilder;
    private static final SQLiteQueryBuilder sImageAndPlaceBuilder;

    static {
        sPlaceAndCategoryBuilder = new SQLiteQueryBuilder();

        sPlaceAndCategoryBuilder.setTables(
                DatabaseConstants.PLACE_TABLE + " INNER JOIN " +
                        DatabaseConstants.CATEGORY_TABLE +
                        " ON " + DatabaseConstants.PLACE_TABLE +
                        "." + DatabaseConstants.CATEGORY_ID +
                        " = " + DatabaseConstants.CATEGORY_TABLE +
                        "." + DatabaseConstants._ID
                        + " LEFT JOIN " +
                        DatabaseConstants.CONVE_IMAGE_TABLE +
                        " ON " + DatabaseConstants.PLACE_TABLE +
                        " . " + DatabaseConstants._ID +
                        " = " + DatabaseConstants.CONVE_IMAGE_TABLE +
                        " . " + DatabaseConstants.PLACE_ID
        );

        sImageAndPlaceBuilder = new SQLiteQueryBuilder();
        sImageAndPlaceBuilder.setTables(
                DatabaseConstants.IMAGE_TABLE
                        + " INNER JOIN " +
                        DatabaseConstants.PLACE_TABLE +
                        " ON " + DatabaseConstants.IMAGE_TABLE +
                        " . " + DatabaseConstants.PLACE_ID +
                        " = " + DatabaseConstants.PLACE_TABLE +
                        " . " + DatabaseConstants._ID
        );
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        travelDbHelper = new TravelDbHelper(context);

        db = travelDbHelper.getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor = null;

        switch (sUriMatcher.match(uri)) {
            //category
            case CATEGORY: {
                retCursor = TravelDbHelper.getInstance(getContext()).getReadableDatabase().query(DatabaseConstants.CATEGORY_TABLE, projection, null, null, null, null, null);
                break;
            }
            case PLACE: {
                //  retCursor = TravelDbHelper.getInstance(getContext()).getReadableDatabase().query(DatabaseConstants.PLACE_TABLE, projection, selection, null, null, null, null);
                retCursor = getPlace(uri, projection, sortOrder);
                break;
            }
            case PLACE_WITH_CATEGORY: {
                retCursor = TravelDbHelper.getInstance(getContext()).getReadableDatabase().query(DatabaseConstants.PLACE_TABLE, projection, selection, selectionArgs, null, null, null);
            }
            case IMAGE: {
                retCursor = TravelDbHelper.getInstance(getContext()).getReadableDatabase().query(DatabaseConstants.IMAGE_TABLE, projection, selection, selectionArgs, null, null, null);
                //  retCursor = getImage(uri, projection, sortOrder);
                break;
            }

        }
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CATEGORY:
                return TravelContract.CategoryEntry.CONTENT_TYPE;
            case PLACE:
                return TravelContract.PlaceEntry.CONTENT_TYPE;
            case IMAGE:
                return TravelContract.ImageEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = TravelDbHelper.getInstance(getContext()).getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri = null;
        switch (match) {
            case CATEGORY: {
                long _id = db.insert(DatabaseConstants.CATEGORY_TABLE, null, values);
                if (_id > 0)
                    returnUri = TravelContract.CategoryEntry.buildCategoryUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case PLACE: {
                long _id = db.insert(DatabaseConstants.PLACE_TABLE, null, values);
                if (_id > 0)
                    returnUri = TravelContract.PlaceEntry.buildPlaceUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case IMAGE: {
                long _id = db.insert(DatabaseConstants.IMAGE_TABLE, null, values);
                if (_id > 0)
                    returnUri = TravelContract.ImageEntry.buildImageUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            case COVER_IMAGE: {
                long _id = db.insert(DatabaseConstants.CONVE_IMAGE_TABLE, null, values);
                if (_id > 0)
                    returnUri = TravelContract.CoverImageEntry.buildCoverImageUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = TravelDbHelper.getInstance(getContext()).getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        int numRowsDeleted = 0;
        if (selection == null) {
            selection = "1";
        }
        switch (match) {
            case CATEGORY: {
                numRowsDeleted = db.delete(DatabaseConstants.CATEGORY_TABLE, selection, selectionArgs);
                break;
            }
            case PLACE: {
                numRowsDeleted = db.delete(DatabaseConstants.PLACE_TABLE, selection, selectionArgs);
                break;
            }
            case IMAGE: {
                numRowsDeleted = db.delete(DatabaseConstants.IMAGE_TABLE, selection, selectionArgs);
                break;
            }
            case COVER_IMAGE: {
                numRowsDeleted = db.delete(DatabaseConstants.CONVE_IMAGE_TABLE, selection, selectionArgs);
                break;
            }
        }
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = TravelDbHelper.getInstance(getContext()).getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowUpdated = 0;
        switch (match) {
            case CATEGORY:
                rowUpdated = db.update(DatabaseConstants.CATEGORY_TABLE, values, selection, selectionArgs);
                break;
            case PLACE:
                rowUpdated = db.update(DatabaseConstants.PLACE_TABLE, values, selection, selectionArgs);
                break;
            case IMAGE:
                rowUpdated = db.update(DatabaseConstants.IMAGE_TABLE, values, selection, selectionArgs);
                break;
            case COVER_IMAGE:
                rowUpdated = db.update(DatabaseConstants.CONVE_IMAGE_TABLE, values, selection, selectionArgs);
                break;
        }
        if (rowUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = TravelContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, TravelContract.PATH_CATEGORY, CATEGORY);
        matcher.addURI(authority, TravelContract.PATH_PLACE, PLACE);
        matcher.addURI(authority, TravelContract.PATH_IMAGE, IMAGE);
        matcher.addURI(authority, TravelContract.PATH_COVER_IMAGE, COVER_IMAGE);
        return matcher;
    }

    private Cursor getPlace(Uri uri, String[] projection, String sortOrder) {
        return sPlaceAndCategoryBuilder.query(TravelDbHelper.getInstance(getContext()).getReadableDatabase(),
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }
}
