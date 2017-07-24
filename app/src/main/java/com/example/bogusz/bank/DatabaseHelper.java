package com.example.bogusz.bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATBASE_NAME = "UzytkownicyBanku.db";
    public static final String TABLE_NAME = "Uzytkownicy_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "IMIE";
    public static final String COL_3 = "NAZWISKO";
    public static final String COL_4 = "LOGIN";
    public static final String COL_5 = "HASLO";
    public static final String COL_6 = "KWOTA_NA_KONCIE";

    public DatabaseHelper(Context context) {
        super(context, DATBASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT," + COL_3 + " TEXT, " + COL_4 + " TEXT, " + COL_5 + " TEXT, "+ COL_6 +" INTEGER) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String imie, String nazwisko, String login, String haslo, int kwota){
        //that add data to SQL

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, imie);
        contentValues.put(COL_3, nazwisko);
        contentValues.put(COL_4, login);
        contentValues.put(COL_5, haslo);
        contentValues.put(COL_6, kwota);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1) // jeśli nie uda się dodać
        {
            return false;
        }else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from" + TABLE_NAME,null);
        return res;
    }
}
