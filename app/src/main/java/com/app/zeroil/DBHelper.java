package com.app.zeroil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final String ZEROIL_DB = "zeroil.db";

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERID = "userid";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FULLNAME = "fname";
    public static final String COLUMN_USEREMAIL = "email";
    public static final String COLUMN_USERCONTACT = "contact";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_DROPOFF = "dropoff";
    public static final String COLUMN_DROPPOFFID = "dropoffid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LONG = "long";
    public static final String COLUMN_CONTACT = "contact";



    public DBHelper(Context context) {
        super(context, ZEROIL_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table " + TABLE_USERS + " ( " + COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
        MyDB.execSQL("create Table " + TABLE_DROPOFF + " ( " + COLUMN_DROPPOFFID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_EMAIL + " TEXT, "  + COLUMN_ADDRESS + " TEXT,  " + COLUMN_LAT + " TEXT, " + COLUMN_LONG + " TEXT, " + COLUMN_CONTACT + " TEXT ) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists " + TABLE_USERS);
      MyDB.execSQL("drop Table if exists " + TABLE_DROPOFF);

    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = MyDB.insert(TABLE_USERS, null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean updatePassword(String username, String ps){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("UPDATE " + TABLE_USERS + " SET " + COLUMN_PASSWORD + " = ?", new String[]{ps});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean updateUsername(String username, String uname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("UPDATE " + TABLE_USERS + " SET " + COLUMN_USERNAME + " = ?", new String[]{uname});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE_USERS + " where " + COLUMN_USERNAME + " = ? and " + COLUMN_PASSWORD + " = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }



    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT  username, password FROM "+ TABLE_USERS;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("username",cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
            user.put("password",cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            userList.add(user);
        }
        return  userList;
    }




    public ArrayList<DropOffModel> GetDropOffLocation(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<DropOffModel> dropofflist = new ArrayList<>();
        String query = "SELECT  * FROM "+ TABLE_DROPOFF;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()) {

            do {
                DropOffModel DO = new DropOffModel();
                DO.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)) );
                DO.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)) );
                DO.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                DO.setContact(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)) );
                DO.setLat(cursor.getString(cursor.getColumnIndex(COLUMN_LAT)) );
                DO.setLongi(cursor.getString(cursor.getColumnIndex(COLUMN_LONG)));
                dropofflist.add( DO );

            } while (cursor.moveToNext());

        }
        return  dropofflist;
    }
}