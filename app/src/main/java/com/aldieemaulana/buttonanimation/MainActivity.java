package com.aldieemaulana.buttonanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.dd.morphingbutton.MorphingButton;

public class MainActivity extends AppCompatActivity {

    static MorphingButton btnMorph;

    @Override
    protected void onResume() {
        super.onResume();

        btnMorphToSquare();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMorph = findViewById(R.id.btnMorph);

        btnMorphToSquare();


        btnMorph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MorphingButton.Params circle = MorphingButton.Params.create()
                        .duration(200)
                        .cornerRadius((int) getResources().getDimension(R.dimen.mb_height_56)) // 56 dp
                        .width((int) getResources().getDimension(R.dimen.mb_height_56)) // 56 dp
                        .height((int) getResources().getDimension(R.dimen.mb_height_56)) // 56 dp
                        .color(getResources().getColor(R.color.colorButtonDefault)) // normal state color
                        .colorPressed(getResources().getColor((R.color.colorButtonPressed))) // pressed state color
                        .icon(R.drawable.ic_more_horiz_white_24dp); // icon
                btnMorph.morph(circle);

                final ObjectAnimator animY = ObjectAnimator.ofFloat(btnMorph, "translationY", -100f, 10f);
                animY.setDuration(700);
                animY.setInterpolator(new LinearOutSlowInInterpolator());
                animY.setInterpolator(new BounceInterpolator());
                animY.setRepeatCount(2);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animY.start();
                    }
                }, 700);

                animY.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startRevealActivity(v);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }
        });
    }

    private void btnMorphToSquare() {
        MorphingButton.Params square;
        square = MorphingButton.Params.create()
                .duration(100)
                .cornerRadius((int) getResources().getDimension(R.dimen.mb_height_56)) // 56 dp
                .width((int) getResources().getDimension(R.dimen.mb_width_200)) // 200 dp
                .height((int) getResources().getDimension(R.dimen.mb_height_56)) // 56 dp
                .color(getResources().getColor(R.color.colorButtonDefault)) // normal state color
                .colorPressed(getResources().getColor((R.color.colorButtonPressed))) // pressed state color
                .text(getResources().getString(R.string.button_sign_in)); // text

        btnMorph.morph(square);
    }


    private void startRevealActivity(View v) {
        //calculates the center of the View v you are passing
        int revealX = (int) (v.getX() + v.getWidth() / 2);
        int revealY = (int) (v.getY() + v.getHeight() / 2);

        //create an intent, that launches the second activity and pass the x and y coordinates
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }

}
