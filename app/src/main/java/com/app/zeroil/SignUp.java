package com.app.zeroil;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPassword;
    Button regBtn, callregToLoginBtn;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        DB = new DBHelper(this);


        //Hooks to all xml elements in activity_sign_up.xml
        regName =findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        callregToLoginBtn = findViewById(R.id.reg_login_btn);
        regBtn = findViewById(R.id.reg_btn);


       //Call log in button
        callregToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


    //Validation for sign up form
    private boolean validateregName(){
        String val = regName.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateregUsername(){
        String val = regUsername.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";

        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length()>20) {
            regUsername.setError("Username is too large");
            return false;
        } else if (!val.matches(checkspaces)) {
            regUsername.setError("No White Spaces are allowed");
            return false;
        }else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateregEmail(){
        String val = regEmail.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-z0-9._]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        } else if(!val.matches(checkEmail)) {
            regEmail.setError("Invalid Email!");
            return false;
        } else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateregPassword(){
        String val = regPassword.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        } else if(!val.matches(checkPassword)) {
            regPassword.setError("Password should contain 4 characters!");
            return false;
        } else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view) {

        //Performing Validation by calling validation functions
        if(!validateregName() | !validateregUsername()  | !validateregEmail() | !validateregPassword()){
            return;
        }

        //Get all the values in String
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();


        Boolean checkuser = DB.checkusernamepassword(username,password);
        if(!checkuser){
            Boolean insert = DB.insertData(username, password,email,name);
            if(insert){
                Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                Intent intentt = new Intent(getApplicationContext(),Success.class);
                startActivity(intentt);
            }else{
                Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(SignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
        }

    }

}

