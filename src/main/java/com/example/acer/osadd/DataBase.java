package com.example.acer.osadd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * Created by Acer on 20/11/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DATA.db";
    public static final String OS_DATA_TABLE_NAME = "OS";
    public static final String OS_DATA_COLUMN_OS = "os_name";
    public static final String OS_DATA_COLUMN_LOGO = "os_logo";
    public static final String OS_DATA_COLUMN_DESCR = "os_desc";
    public static final String CONTACTS_COLUMN_N_USER = "os_stat_n_user";
    public static final String CONTACTS_COLUMN_N_VERS = "os_stat_n_vers";
    public static final String CONTACTS_COLUMN_N_SMART = "os_stat_n_smart";
    private SQLiteDatabase db;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table  " + OS_DATA_TABLE_NAME +
                        "("+ OS_DATA_COLUMN_OS +" text primary key, "+OS_DATA_COLUMN_LOGO+" text,"+
                        OS_DATA_COLUMN_DESCR+" text,"+CONTACTS_COLUMN_N_USER+" integer,"+
                        CONTACTS_COLUMN_N_VERS+" integer,"+CONTACTS_COLUMN_N_SMART+" integer)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+OS_DATA_TABLE_NAME);
        onCreate(db);

    }

    public void open()
    {
        db = this.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }


    public boolean insert_OS_data (Fichier_info info) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(OS_DATA_COLUMN_OS, info.getOps());
        contentValues.put(OS_DATA_COLUMN_LOGO, info.getLogo());
        contentValues.put(OS_DATA_COLUMN_DESCR, info.getDescription());
        contentValues.put(CONTACTS_COLUMN_N_USER, info.getNombre_user());
        contentValues.put(CONTACTS_COLUMN_N_VERS, info.getNombre_version());
        contentValues.put(CONTACTS_COLUMN_N_SMART, info.getNombre_smart());
        long result =    db.insert(OS_DATA_TABLE_NAME, null, contentValues);
        if (result==-1)
            return false;
        return true;
    }


    public Fichier_info getOS_data(String os) {

        Fichier_info info =null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(OS_DATA_TABLE_NAME, new String[]{OS_DATA_COLUMN_OS,
                OS_DATA_COLUMN_LOGO, OS_DATA_COLUMN_DESCR, CONTACTS_COLUMN_N_USER, CONTACTS_COLUMN_N_VERS,
                CONTACTS_COLUMN_N_SMART}, OS_DATA_COLUMN_OS + "=?", new String[]{os}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        if(!cursor.isAfterLast())
            info = new Fichier_info(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return info;
    }
}