package com.example.myapplication.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Databases;

import java.util.ArrayList;

public class Path {
    private ArrayList<String> pathArr;
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private static DatabaseHelper mDBHelper;
    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(Databases.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+Databases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }
    public static class DbOpenHelper {
        private Context mCtx;

        public DbOpenHelper(Context context){
            this.mCtx = context;
        }

        public DbOpenHelper open() throws SQLException {
            mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
            mDB = mDBHelper.getWritableDatabase();
            return this;
        }
        public void create(){
            mDBHelper.onCreate(mDB);
        }
        public void close(){ mDB.close(); }
    }
    public void setPath(ArrayList<String> pathArr){
        this.pathArr = pathArr;
    }
    public ArrayList<String> getPathArr(){
        return pathArr;
    }

    public long save(){
        String path = "";
        for(int i = 0; i<pathArr.size(); i++){
            String tmp = pathArr.get(i)+" ";
            path+= tmp;
        }
        ContentValues values = new ContentValues();

        values.put(Databases.CreateDB.PATH, path);

        return mDB.insert(Databases.CreateDB._TABLENAME0, null, values);
    }
}
