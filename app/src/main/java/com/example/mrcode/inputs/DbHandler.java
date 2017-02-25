package com.example.mrcode.inputs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrCode on 23/02/2017.
 */

public class DbHandler extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "paymentInfo";

    public static final String TABLE_NAME = "payment";
    // amounts table column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AMOUNTS = "amounts";


    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_AMOUNTS + " INTEGER)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Creating tables again
        onCreate(db);
    }

    public void addContent(Payment payment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, payment.getName()); // testing
        values.put(COLUMN_AMOUNTS, payment.getAmount()); // testing

        // inserting row
        db.insert(TABLE_NAME, null, values);
        db.close(); // closing database connection

    }

    public Payment getContent(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_AMOUNTS
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                COLUMN_NAME + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                // The sort order
        );


        cursor.moveToFirst();
        Payment out = new Payment(
                Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        db.close();
        cursor.close();
        return out;
       /* List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(_ID));
            itemIds.add(itemId);
        }
        cursor.close();*/


    }

    public void DeletePayment(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public void DeleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
    }

    public List<Payment> getAllPayment() {
        List<Payment> outList = new ArrayList<Payment>();
        // select all values query
        String selectQry = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry, null);
        // looping all rows and adding to the list
        if (cursor.moveToFirst()) {
            do{
                Payment out = new Payment();
                out.setId(Integer.parseInt(cursor.getString(0)));
                out.setName(cursor.getString(1));
                out.setAmount(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                outList.add(out);
            }while (cursor.moveToNext());
        }

        // return the list of payment
        return outList;

        }

    // updating the payment
    public int updatePayment(Payment payment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, payment.getName());
        values.put(COLUMN_AMOUNTS, payment.getAmount());
        // update the row
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(payment.getId())});
    }
    }



