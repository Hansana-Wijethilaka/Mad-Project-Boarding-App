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

    private static final int VERSION = 1;
    private static final String DB_NAME = "hostal_db";
    private static final String TABLE_NAME = "hostal_db";

    private static final String ID = "id";
    private static final String OWNER_NAME = "owner_name";
    private static final String HOSTAL_LOCATION = "hostal_location";
    private static final String PHONE_NUM = "phone_num";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String NUM_OF_RM = "num_of_rm";
    private static final String PRICE = "price";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";


    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +OWNER_NAME + " TEXT,"
                +HOSTAL_LOCATION + " TEXT,"
                +PHONE_NUM + " TEXT,"
                +EMAIL + " TEXT,"
                +ADDRESS + " TEXT,"
                +NUM_OF_RM + " INT,"
                +PRICE + " INT,"
                +STARTED+ " TEXT,"
                +FINISHED+ " TEXT" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;

        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);

        onCreate(sqLiteDatabase);

    }

    public void add_Hostal(Hostal hostal){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(OWNER_NAME,hostal.getOwner_name());
        contentValues.put(HOSTAL_LOCATION,hostal.getHostal_location());
        contentValues.put(PHONE_NUM,hostal.getPhone_num());
        contentValues.put(EMAIL,hostal.getEmail());
        contentValues.put(ADDRESS,hostal.getAddress());
        contentValues.put(NUM_OF_RM,hostal.getNum_of_rm());
        contentValues.put(PRICE,hostal.getPrice());
        contentValues.put(STARTED,hostal.getStarted());
        contentValues.put(FINISHED,hostal.getFinished());

        sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
        sqLiteDatabase.close();

    }

    public  int countHostal(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }

    public List<Hostal> getAllHostal(){

        List<Hostal> hostals = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {

                Hostal hostal = new Hostal();

                hostal.setId(cursor.getInt(0));
                hostal.setOwner_name(cursor.getString(1));
                hostal.setHostal_location(cursor.getString(2));
                hostal.setPhone_num(cursor.getString(3));
                hostal.setEmail(cursor.getString(4));
                hostal.setAddress(cursor.getString(5));
                hostal.setNum_of_rm(cursor.getString(6));
                hostal.setPrice(cursor.getString(7));
                hostal.setStarted(cursor.getLong(8));
                hostal.setFinished(cursor.getLong(9));

                hostals.add(hostal);

            }while (cursor.moveToNext());
        }
        return hostals;
    }

    public void deleteHostal(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public Hostal getSingaleHostal(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{ID,OWNER_NAME,HOSTAL_LOCATION,PHONE_NUM,
                        EMAIL,ADDRESS,NUM_OF_RM,PRICE,STARTED,FINISHED},
                ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

        Hostal hostal;

        if (cursor != null){
            cursor.moveToFirst();
           hostal = new Hostal(
                   cursor.getInt(0),
                   cursor.getString(1),
                   cursor.getString(2),
                   cursor.getString(3),
                   cursor.getString(4),
                   cursor.getString(5),
                   cursor.getString(6),
                   cursor.getString(7),
                   cursor.getLong(8),
                   cursor.getLong(9)
           );
           return hostal;

        }
        return null;

    }

    public int updateHostal(Hostal hostal){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(OWNER_NAME,hostal.getOwner_name());
        contentValues.put(HOSTAL_LOCATION,hostal.getHostal_location());
        contentValues.put(PHONE_NUM,hostal.getPhone_num());
        contentValues.put(EMAIL,hostal.getEmail());
        contentValues.put(ADDRESS,hostal.getAddress());
        contentValues.put(NUM_OF_RM,hostal.getNum_of_rm());
        contentValues.put(PRICE,hostal.getPrice());
        contentValues.put(STARTED,hostal.getStarted());
        contentValues.put(FINISHED,hostal.getFinished());

        int status = sqLiteDatabase.update(TABLE_NAME,contentValues,
                ID +" =?",
                new String[]{String.valueOf(hostal.getId())});

        sqLiteDatabase.close();

        return status;
    }

}
