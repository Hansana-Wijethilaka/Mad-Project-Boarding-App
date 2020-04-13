package com.example.boadme;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}