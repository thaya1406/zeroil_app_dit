package com.app.zeroil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName, email, username, password;
    TextView fullNameLabel, usernameLabel;
    Button updatebtn;
    //Global Variables to hold user data inside this activity
    String _USERNAME, _NAME, _EMAIL, _PASSWORD,untoserach;
    SharedPreferences prf;
    SharedPreferences.Editor editor ;
    Context context;



    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FULLNAME = "fname";
    public static final String COLUMN_USEREMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public final DBHelper helper = new DBHelper(this);
    SQLiteDatabase MyDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


         MyDB = helper.getWritableDatabase();
        prf = getSharedPreferences("user_details", MODE_PRIVATE);
        //Hooks
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        username = findViewById(R.id.username_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
        updatebtn = findViewById(R.id.updatebtn);

        showAllUserData();

        updatebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             Toast.makeText(UserProfile.this, "Data Updated Succesfully",Toast.LENGTH_LONG).show();

               update( );
           }
           });


    }




    private void showAllUserData() {

        untoserach = prf.getString("username", null);

        Cursor cursor;
        cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{untoserach});

        if (cursor.moveToFirst()) {

            do {
                UserModel UO = new UserModel();

                UO.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                UO.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                UO.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USEREMAIL)));
                UO.setName(cursor.getString(cursor.getColumnIndex(COLUMN_FULLNAME)));

                Intent intent = getIntent();

                _USERNAME = UO.getUsername();
                _NAME = UO.getName();
                _EMAIL = UO.getEmail();
                _PASSWORD = UO.getPassword();
            } while (cursor.moveToNext());

        }

        //ShowAllData
        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        username.getEditText().setText(_USERNAME);
        password.getEditText().setText(_PASSWORD);

    }


    public void update( ) {

        if (isUserNameChanged() || isPasswordChanged() || isNameChanged()  || isEmailChanged() ) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();

        }

    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())) {
            String pstochange = password.getEditText().getText().toString();
            String strSQL;
            strSQL = String.format("UPDATE  users  SET password = '%s' WHERE username = '%s'",pstochange,untoserach);
            MyDB.execSQL(strSQL);
            _PASSWORD = password.getEditText().getText().toString();

            return true;
        } else {
            return false;
        }
    }

    private boolean isUserNameChanged() {

        if (!_USERNAME.equals(username.getEditText().getText().toString())) {
            String ustochnage = username.getEditText().getText().toString();
            String nametoearch= fullName.getEditText().getText().toString();
            String strSQL;
            strSQL = String.format("UPDATE  users  SET username = '%s' WHERE fname = '%s'",ustochnage,nametoearch);
            MyDB.execSQL(strSQL);
            _USERNAME = username.getEditText().getText().toString();
            username.getEditText().setText(_USERNAME);
            usernameLabel.setText(_USERNAME);
            untoserach=ustochnage;

         /**   SharedPreferences settings = context.getSharedPreferences("username", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            editor = prf.edit();
            editor.putString("username",untoserach);
            editor.commit();**/

            return true;
        } else {
            return false;
        }


    }

    private boolean isNameChanged() {

        if (!_NAME.equals(fullName.getEditText().getText().toString())) {
            String nmtochnage = fullName.getEditText().getText().toString();
            String strSQL;
            strSQL = String.format("UPDATE  users  SET fname = '%s' WHERE username = '%s'",nmtochnage,untoserach);
            MyDB.execSQL(strSQL);
            _NAME = fullName.getEditText().getText().toString();
            fullName.getEditText().setText(_NAME);
            fullNameLabel.setText(_NAME);
            fullName.setError(null);
            return true;
        } else {
            return false;
        }


    }

    private boolean isEmailChanged() {

        if (!_EMAIL.equals(email.getEditText().getText().toString())) {
            String emtochange = email.getEditText().getText().toString();
            String strSQL;
            strSQL = String.format("UPDATE  users  SET email = '%s' WHERE username = '%s'",emtochange,untoserach);
            MyDB.execSQL(strSQL);
            _EMAIL = email.getEditText().getText().toString();
            email.getEditText().setText(_EMAIL);
            return true;
        } else {
            return false;
        }


    }
}