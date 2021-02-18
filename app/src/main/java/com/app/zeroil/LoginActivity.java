package com.app.zeroil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Pair;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    DBHelper DB;
    Button callSignUp,login_btn;
    TextInputLayout username, password;
    ProgressBar progressBar;
    TextView logoText, sloganText;
    ImageView image;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        callSignUp = findViewById(R.id.signup_screen);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        login_btn = findViewById(R.id.login_btn);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);




        DB = new DBHelper(this);

        callSignUp.setOnClickListener(view -> {
            callSignUpScreen();

        });



    }





    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        //Validate login info
        if (!validateUsername() | !validatePassword()) {
        } else {
            isUser();
        }
    }








    private void isUser() {
        progressBar.setVisibility(View.VISIBLE);

        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username",userEnteredUsername);
        editor.commit();
        Boolean checkuser = DB.checkusernamepassword(userEnteredUsername, userEnteredPassword);
        if (checkuser) {
            Toast.makeText(this, "Login Succesfull", Toast.LENGTH_SHORT).show();
            Intent intentt = new Intent(getApplicationContext(), HomePageActivity.class);
            startActivity(intentt);

        } else {
            progressBar.setVisibility(View.GONE);
            username.setError("No such User exist! Check your username or register now !");
            password.setError("No such User exist! Check your password or register now !");

            username.requestFocus();
            password.requestFocus();

        }



    }



    //Call SignUp Screen
    public void callSignUpScreen() {
        //To call next activity
        Intent intent = new Intent(this, SignUp.class);

        //create pairs for animation
        Pair[] pairs = new Pair[7];
        pairs[0] = new Pair<View, String>(image, "logo_image"); //1st one is the element and 2nd is the transition name of animation.
        pairs[1] = new Pair<View, String>(logoText, "logo_text");
        pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
        pairs[3] = new Pair<View, String>(username, "username_tran");
        pairs[4] = new Pair<View, String>(password, "password_tran");
        pairs[5] = new Pair<View, String>(login_btn, "button_tran");
        pairs[6] = new Pair<View, String>(callSignUp, "login_signup_tran");

        //Call next activity by attaching the animation with it.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
            startActivity(intent, options.toBundle());
        }
    }





}