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
    private static final String TABLE_NAME = "hostal_db";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CONTACT = "contact";
    private static final String AGE = "age";
    private static final String GENDER = "gender";
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
                +NAME + " TEXT,"
                +CONTACT + " TEXT,"
                +AGE + " TEXT,"
                +GENDER + " TEXT,"
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

        contentValues.put(NAME,hostal.getName());
        contentValues.put(CONTACT,hostal.getContact());
        contentValues.put(AGE,hostal.getAge());
        contentValues.put(GENDER,hostal.getGender());
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
                hostal.setName(cursor.getString(1));
                hostal.setContact(cursor.getString(2));
                hostal.setAge(cursor.getString(3));
                hostal.setGender(cursor.getString(4));
                hostal.setStarted(cursor.getLong(5));
                hostal.setFinished(cursor.getLong(6));

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

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{ID,NAME,CONTACT,AGE,
                        GENDER,STARTED,FINISHED},
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
                    cursor.getLong(5),
                    cursor.getLong(6)
            );
            return hostal;

        }
        return null;

    }

    public int updateHostal(Hostal hostal){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,hostal.getName());
        contentValues.put(CONTACT,hostal.getContact());
        contentValues.put(AGE,hostal.getAge());
        contentValues.put(GENDER,hostal.getGender());
        contentValues.put(STARTED,hostal.getStarted());
        contentValues.put(FINISHED,hostal.getFinished());

        int status = sqLiteDatabase.update(TABLE_NAME,contentValues,
                ID +" =?",
                new String[]{String.valueOf(hostal.getId())});

        sqLiteDatabase.close();

        return status;
    }

}
