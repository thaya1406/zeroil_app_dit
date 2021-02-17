package com.app.zeroil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
    private ProgressDialog loadingg;
    private AlertDialog dialog;
    //Global Variables to hold user data inside this activity
    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;
    String name;
    String namee;

    SharedPreferences prf;
    SharedPreferences trial;


DBHelper DB;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERID = "userid";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FULLNAME = "fname";
    public static final String COLUMN_USEREMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public final DBHelper helper = new DBHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        //Hooks
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        username = findViewById(R.id.username_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
        updatebtn = findViewById(R.id.updatebtn);

        loadingg = new ProgressDialog(this);
        loadingg.setIndeterminate(true);
        loadingg.setCancelable(false);
        loadingg.setCanceledOnTouchOutside(false);



        String untoserach = prf.getString("username",null);

        SQLiteDatabase MyDB = helper.getWritableDatabase();
        Cursor cursor;
        cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{untoserach});

        if(cursor.moveToFirst()) {

            do {
                UserModel UO = new UserModel();

                UO.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                UO.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)) );
                UO.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USEREMAIL)) );
                UO.setName(cursor.getString(cursor.getColumnIndex(COLUMN_FULLNAME)) );

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


         updatebtn.setOnClickListener(this::onClick);

    }

    public void update() {
        Boolean checkuser;
         checkuser = DB.updatePassword(_USERNAME, password.getEditText().getText().toString());
        _PASSWORD = password.getEditText().getText().toString();
        password.getEditText().setText(_PASSWORD);

         checkuser = DB.updateUsername(_USERNAME, username.getEditText().getText().toString());
        _USERNAME = username.getEditText().getText().toString();
        username.getEditText().setText(_USERNAME);

        checkuser = DB.updateEmail(_EMAIL, email.getEditText().getText().toString());
        _EMAIL = email.getEditText().getText().toString();
        email.getEditText().setText(_EMAIL);

        checkuser = DB.updateName(_NAME, fullName.getEditText().getText().toString());
        _NAME = fullName.getEditText().getText().toString();
        fullName.getEditText().setText(_NAME);


        if(checkuser)
        {   Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();}

        else
        {Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_LONG).show();}

    }

  /**  private boolean isPasswordChanged() {
     try {
         if (!_PASSWORD.equals(password.getEditText().getText().toString())) {
             Boolean checkuser = DB.updatePassword(_USERNAME, password.getEditText().getText().toString());
             _PASSWORD = password.getEditText().getText().toString();
             password.getEditText().setText(_PASSWORD);

             return true;
         } else {
             return false;
         }
     }

     catch (Exception e)
     {

         Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
         return false;

     }
    }**/

    /** private boolean isUserNameChanged() {

        if (!_USERNAME.equals(username.getEditText().getText().toString())) {

            Boolean checkuser = DB.updateUsername(_USERNAME, username.getEditText().getText().toString());
            _USERNAME = username.getEditText().getText().toString();
            username.getEditText().setText(_USERNAME);
            return true;
        } else {
            return false;
        }

    }**/

    private void onClick(View view)
    {
        update();
    }
}
