package com.example.tubes1;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract(){}

    public static class FeedDokter implements BaseColumns{
        public static final String TABLE_NAME = "Dokter";
        public static final String COLUMN_NAME_NAMA = "nama";
        public static final String COLUMN_NAME_SPESIALIS = "spesialis";
        public static final String COLUMN_NAME_NOTELEPON = "telepon";
    }

    public static class FeedAppointment implements BaseColumns{
        public static final String TABLE_NAME = "Appointment";
        public static final String COLUMN_NAME_NAMA_DOKTER = "namaDokter";
        public static final String COLUMN_NAME_NAMA_PASIEN = "namaPasien";
        public static final String COLUMN_NAME_TANGGAL = "tanggal";
        public static final String COLUMN_NAME_KELUHAN = "keluhan";
        public static final String COLUMN_NAME_WAKTU = "waktu";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
