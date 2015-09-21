package com.mycompany.countrydictionary2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;
import java.sql.SQLException;

/**
 * Created by mamram on 9/6/2015.
 */
public class DbAdapter {

    public static final String key_country = "Country";
    public static final String key_capital = "Capital";
    public static final String key_continent = "Continent";
    private static final String tag = "DbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String database_create = "create table countries (Country text primary key, Capital text not null, Continent text not null);";
    private static final String database_name = "data_countries";
    private static final String database_table = "countries";
    private static final int    database_version=2;
    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context){super(context,database_name,null,database_version);}

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(database_create);
        }

        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion,int newVersion){
            Log.w(tag, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS countries");
            onCreate(db);
        }
    }

    public DbAdapter(Context ctx){this.mCtx=ctx;}

    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){mDbHelper.close();}

    public Cursor fetchAllNotes() {

        return mDb.query(database_table, new String[] {key_country, key_capital,
                key_continent}, null, null, null, null, null);
    }

    public Cursor fetchCountry (String country) throws SQLException{
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor mCursor=
                mDb.query(true,database_table,new String[]{key_country,key_capital,key_continent},key_country +"=" + "'"+ country+"'",null,null,null,null,null);
        if(mCursor!=null){
            mCursor.moveToFirst();
        }
        return mCursor;



    }


}
