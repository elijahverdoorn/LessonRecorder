package com.example.elijah.lessonrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Elijah on 4/1/2015.
 */
public class DBTools extends SQLiteOpenHelper {

    public DBTools(Context applicationContext) {
        super(applicationContext, "tracks.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the database table
        //TODO: insert more fields
        String query = "CREATE TABLE tracks (trackID INTEGER PRIMARY KEY, trackName TEXT)";


        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS tracks";
        db.execSQL(query);
        onCreate(db);
    }

    // TODO: understand how the hash maps work
    public void insertTrack(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("trackName", queryValues.get("trackName"));

        database.insert("tracks", null, values);

        database.close();
    }

    // does this just update the trackname?
    public int updateTrack(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("trackName", queryValues.get("trackName"));

        return database.update("tracks", values, "trackID" + " = ?", new String[]{queryValues.get("trackID")});
    }

    public void deleteTrack(String id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM tracks WHERE trackID='" + id + "'";

        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllTracks() {
        ArrayList<HashMap<String, String>> trackArrayList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM tracks";
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> trackMap = new HashMap<String, String>();

                trackMap.put("trackID", cursor.getString(0)); // 0 since track name is index 0
                trackMap.put("trackName", cursor.getString(1));

                trackArrayList.add(trackMap);
            } while (cursor.moveToNext());
        }

        return trackArrayList;
    }

    public HashMap<String, String> getTrackInfo(String id) {
        HashMap<String, String> trackMap = new HashMap<String, String>();

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM tracks WHERE trackID ='" + id + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                trackMap.put("trackID", cursor.getString(0)); // 0 since track name is index 0
                trackMap.put("trackName", cursor.getString(1));

            } while (cursor.moveToNext());
        }
        return trackMap;
    }
}