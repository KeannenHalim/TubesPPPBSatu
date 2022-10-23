package com.example.tubes1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String SQL_CREATE_ENTRIES_DOKTER =
            "CREATE TABLE " + FeedReaderContract.FeedDokter.TABLE_NAME +" ("+
                    FeedReaderContract.FeedDokter._ID+" INTEGER PRIMARY KEY,"+
                    FeedReaderContract.FeedDokter.COLUMN_NAME_NAMA + " VARCHAR(255),"+
                    FeedReaderContract.FeedDokter.COLUMN_NAME_SPESIALIS+" VARCHAR(255),"+
                    FeedReaderContract.FeedDokter.COLUMN_NAME_NOTELEPON+" VARCHAR(255))";
    private static final String SQL_DELETE_ENTRIES_DOKTER =
            "DROP TABLE IF EXISTS "+ FeedReaderContract.FeedDokter.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_APPOINTMENT =
            "CREATE TABLE " + FeedReaderContract.FeedAppointment.TABLE_NAME +" ("+
                    FeedReaderContract.FeedAppointment._ID+" INTEGER PRIMARY KEY,"+
                    FeedReaderContract.FeedAppointment.COLUMN_NAME_NAMA_DOKTER + " VARCHAR(255),"+
                    FeedReaderContract.FeedAppointment.COLUMN_NAME_NAMA_PASIEN+" VARCHAR(255),"+
                    FeedReaderContract.FeedAppointment.COLUMN_NAME_KELUHAN+" VARCHAR(255),"+
                    FeedReaderContract.FeedAppointment.COLUMN_NAME_TANGGAL+" VARCHAR(255),"+
                    FeedReaderContract.FeedAppointment.COLUMN_NAME_WAKTU+" VARCHAR(255),"+
                    FeedReaderContract.FeedAppointment.COLUMN_NAME_STATUS+" BOOLEAN)";
    private static final String SQL_DELETE_ENTRIES_APPOINTMENT =
            "DROP TABLE IF EXISTS "+ FeedReaderContract.FeedAppointment.TABLE_NAME;

    public FeedReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_DOKTER);
        db.execSQL(SQL_CREATE_ENTRIES_APPOINTMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_DOKTER);
        db.execSQL(SQL_DELETE_ENTRIES_APPOINTMENT);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
