package com.example.boadme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DB_NAME = "hostal_db";
    private static final String TABLE_Booking = "booking_db";

    private static final String BOOKING_ID = "booking_id";
    private static final String BOOKING_NAME = "booking_name";
    private static final String BOOKING_CONTACT = "booking_contact";
    private static final String BOOKING_AGE = "booking_age";
    private static final String BOOKING_GENDER = "booking_gender";
    private static final String BOOKING_STARTED = "booking_started";
    private static final String BOOKING_FINISHED = "booking_finished";


    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_CREATE_QUERY_Booking= "CREATE TABLE "+TABLE_Booking+" " +
                "("
                +BOOKING_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +BOOKING_NAME + " TEXT,"
                +BOOKING_CONTACT + " TEXT,"
                +BOOKING_AGE + " TEXT,"
                +BOOKING_GENDER + " TEXT,"
                +BOOKING_STARTED+ " TEXT,"
                +BOOKING_FINISHED+ " TEXT" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY_Booking);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_TABLE_QUERY_Booking = "DROP TABLE IF EXISTS "+ TABLE_Booking;

        sqLiteDatabase.execSQL(DROP_TABLE_QUERY_Booking);

        onCreate(sqLiteDatabase);

    }

    public void add_Booking(Booking booking){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BOOKING_NAME,booking.getName());
        contentValues.put(BOOKING_CONTACT,booking.getContact());
        contentValues.put(BOOKING_AGE,booking.getAge());
        contentValues.put(BOOKING_GENDER,booking.getGender());
        contentValues.put(BOOKING_STARTED,booking.getStarted());
        contentValues.put(BOOKING_FINISHED,booking.getFinished());

        sqLiteDatabase.insert(TABLE_Booking, null,contentValues);
        sqLiteDatabase.close();

    }



    public List<Booking> getAllBooking(){

        List<Booking> bookings = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_Booking;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {

                Booking booking = new Booking();

                booking.setId(cursor.getInt(0));
                booking.setName(cursor.getString(1));
                booking.setContact(cursor.getString(2));
                booking.setAge(cursor.getString(3));
                booking.setGender(cursor.getString(4));
                booking.setStarted(cursor.getLong(5));
                booking.setFinished(cursor.getLong(6));

                bookings.add(booking);

            }while (cursor.moveToNext());
        }
        return bookings;
    }

    public void delete_Booking(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.delete(TABLE_Booking,"booking_id =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public Booking getSingleBooking(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_Booking, new String[]{BOOKING_ID,BOOKING_NAME,BOOKING_CONTACT,BOOKING_AGE,
                        BOOKING_GENDER,BOOKING_STARTED,BOOKING_FINISHED},
                BOOKING_ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

        Booking booking;

        if (cursor != null){
            cursor.moveToFirst();
            booking = new Booking(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getLong(5),
                    cursor.getLong(6)
            );
            return booking;

        }
        return null;

    }

    public int update_Booking(Booking booking){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BOOKING_NAME,booking.getName());
        contentValues.put(BOOKING_CONTACT,booking.getContact());
        contentValues.put(BOOKING_AGE,booking.getAge());
        contentValues.put(BOOKING_GENDER,booking.getGender());
        contentValues.put(BOOKING_STARTED,booking.getStarted());
        contentValues.put(BOOKING_FINISHED,booking.getFinished());

        int status = sqLiteDatabase.update(TABLE_Booking,contentValues,
                BOOKING_ID +" =?",
                new String[]{String.valueOf(booking.getId())});

        sqLiteDatabase.close();

        return status;
    }

}
