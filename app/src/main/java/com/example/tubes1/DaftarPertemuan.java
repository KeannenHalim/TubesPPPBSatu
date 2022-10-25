package com.example.tubes1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DaftarPertemuan {
    private FeedReaderDbHelper dbHelper;

    public DaftarPertemuan(FeedReaderDbHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public Pertemuan addToDb (Pertemuan pertemuan){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedAppointment.COLUMN_NAME_NAMA_PASIEN,pertemuan.getNamaPasien());
        values.put(FeedReaderContract.FeedAppointment.COLUMN_NAME_NAMA_DOKTER,pertemuan.getNamaDokter());
        values.put(FeedReaderContract.FeedAppointment.COLUMN_NAME_KELUHAN,pertemuan.getKeluhan());
        values.put(FeedReaderContract.FeedAppointment.COLUMN_NAME_TANGGAL,pertemuan.getTanggal());
        values.put(FeedReaderContract.FeedAppointment.COLUMN_NAME_WAKTU,pertemuan.getWaktu());
        values.put(FeedReaderContract.FeedAppointment.COLUMN_NAME_STATUS,pertemuan.getStatus());
        long newRowId = db.insert(FeedReaderContract.FeedAppointment.TABLE_NAME,null,values);
        pertemuan.setId((int)newRowId);
        return pertemuan;
    }

    public List<Pertemuan> getFromDb(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedAppointment.COLUMN_NAME_NAMA_PASIEN,
                FeedReaderContract.FeedAppointment.COLUMN_NAME_NAMA_DOKTER,
                FeedReaderContract.FeedAppointment.COLUMN_NAME_KELUHAN,
                FeedReaderContract.FeedAppointment.COLUMN_NAME_TANGGAL,
                FeedReaderContract.FeedAppointment.COLUMN_NAME_WAKTU,
                FeedReaderContract.FeedAppointment.COLUMN_NAME_STATUS
        };

        String sortOrder =
                FeedReaderContract.FeedAppointment._ID + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedAppointment.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Pertemuan> pertemuans = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedAppointment._ID)
            );

            String namaPasien = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedAppointment.COLUMN_NAME_NAMA_PASIEN))
            );

            String namaDokter = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedAppointment.COLUMN_NAME_NAMA_DOKTER))
            );

            String keluhan = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedAppointment.COLUMN_NAME_KELUHAN))
            );

            String tanggal = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedAppointment.COLUMN_NAME_TANGGAL))
            );

            String waktu = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedAppointment.COLUMN_NAME_WAKTU))
            );

            String status = cursor.getString(
                    cursor.getColumnIndexOrThrow((FeedReaderContract.FeedAppointment.COLUMN_NAME_STATUS))
            );

            Boolean status1 = false;
            if(status.equals("1")){
                status1 = true;
            }
            pertemuans.add(new Pertemuan(namaDokter,namaPasien,keluhan,tanggal,waktu,id,status1));
        }
        cursor.close();
        return pertemuans;
    }

    public void deleteFromDb(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = FeedReaderContract.FeedAppointment._ID + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
        int deletedRows = db.delete(FeedReaderContract.FeedAppointment.TABLE_NAME, selection, selectionArgs);
    }

    public void updateFromDb(Pertemuan pertemuan){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FeedReaderContract.FeedAppointment.COLUMN_NAME_STATUS, pertemuan.getStatus());
        String selection = FeedReaderContract.FeedAppointment._ID + " = ?";
        String[] selectionArgs = { Integer.toString(pertemuan.getId()) };
        int updateRows = db.update(FeedReaderContract.FeedAppointment.TABLE_NAME,cv, selection, selectionArgs);
    }
}
