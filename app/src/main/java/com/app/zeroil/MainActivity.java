package com.app.zeroil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ActivityOptions;
import android.os.Handler;
import android.util.Pair;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView  slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //Hooks
        image = findViewById(R.id.imageView);
        slogan = findViewById(R.id.textView2);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);

        ConstraintLayout layout = findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Call next screen
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                //Attach all the elements those you want to animate in design
                Pair[]  pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(image, "logo_image");

                //wrap the call in API level 21 or higher
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Call next screen
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                //Attach all the elements those you want to animate in design
                Pair[]  pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(image, "logo_image");

                //wrap the call in API level 21 or higher
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }

            }
        }, SPLASH_SCREEN);

    }
}
