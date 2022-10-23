package com.example.tubes1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DaftarDokter {
    private FeedReaderDbHelper dbHelper;
    public DaftarDokter(FeedReaderDbHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public Dokter addToDb (Dokter dokter){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedDokter.COLUMN_NAME_NAMA, dokter.getNama());
        values.put(FeedReaderContract.FeedDokter.COLUMN_NAME_SPESIALIS, dokter.getSpecialist());
        values.put(FeedReaderContract.FeedDokter.COLUMN_NAME_NOTELEPON, dokter.getNoTelepon());

        long newRowId = db.insert(FeedReaderContract.FeedDokter.TABLE_NAME,null,values);
        dokter.setId((int)newRowId);
        return dokter;
    }

    public List<Dokter> getFromDb(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedDokter.COLUMN_NAME_NAMA,
                FeedReaderContract.FeedDokter.COLUMN_NAME_SPESIALIS,
                FeedReaderContract.FeedDokter.COLUMN_NAME_NOTELEPON
        };

        String sortOrder =
                FeedReaderContract.FeedDokter._ID + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedDokter.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Dokter> dokters = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedDokter._ID)
            );

            String nama = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedDokter.COLUMN_NAME_NAMA))
            );

            String spesialis = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedDokter.COLUMN_NAME_NAMA))
            );

            String telepon = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedDokter.COLUMN_NAME_NOTELEPON))
            );

            dokters.add(new Dokter(nama,spesialis,telepon,id));
        }
        cursor.close();
        return dokters;
    }

    public void deleteFromDb(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = FeedReaderContract.FeedDokter._ID + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
        int deletedRows = db.delete(FeedReaderContract.FeedDokter.TABLE_NAME, selection, selectionArgs);
    }
}
