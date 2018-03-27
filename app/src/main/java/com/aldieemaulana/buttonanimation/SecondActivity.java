package com.aldieemaulana.buttonanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {

    RevealAnimation mRevealAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = this.getIntent();

        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.root_layout); //there you have to get the root layout of your second activity
        mRevealAnimation = new RevealAnimation(rootLayout, intent, this);
    }

    @Override
    public void onBackPressed()
    {
        mRevealAnimation.unRevealActivity();
    }


}
