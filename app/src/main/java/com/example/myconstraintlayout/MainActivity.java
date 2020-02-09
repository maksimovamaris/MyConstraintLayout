package com.example.myconstraintlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Switch;

import static android.animation.ValueAnimator.ofFloat;

public class MainActivity extends AppCompatActivity {
   private  ConstraintSet constraintSet1=new ConstraintSet();
    private  ConstraintSet constraintSet2=new ConstraintSet();
    private boolean isOffScreen=true;
    private ConstraintLayout mLayout;
    private ImageView mRocket;
    private Switch mSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keyframe1);
        mRocket=findViewById(R.id.rocketIcon1);
        mLayout=findViewById(R.id.constraintLayout);
        mSwitch=findViewById(R.id.switch1);
    constraintSet1.clone(mLayout);
    constraintSet2.clone(this,R.layout.activity_main);
        findViewById(R.id.departButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConstraintLayout.LayoutParams params=(ConstraintLayout.LayoutParams)(mRocket.getLayoutParams());
                float startAngle=params.circleAngle;
                int addAngle=180;

                if   (mSwitch.isChecked())
                {
                    addAngle=360;
                }
                float endAngle=startAngle+addAngle;
                ValueAnimator animator= ValueAnimator.ofFloat(startAngle,endAngle);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Float animValue=(Float)(animation.getAnimatedValue());
                        ConstraintLayout.LayoutParams params=(ConstraintLayout.LayoutParams)(mRocket.getLayoutParams());
                        params.circleAngle=animValue;
                        mRocket.setLayoutParams(params);
                        mRocket.setRotation(animValue%360-270);

                    }

                });
                if(mSwitch.isChecked())
                animator.setDuration(2000);
                else
                    animator.setDuration(1000);
                animator.setInterpolator(new LinearInterpolator());
                animator.start();
//                anim.addUpdateListener { valueAnimator ->
//
//                        //3
//                        val animatedValue = valueAnimator.animatedValue as Float
//                    val layoutParams = rocketIcon.layoutParams as ConstraintLayout.LayoutParams
//                    layoutParams.circleAngle = animatedValue
//                    rocketIcon.layoutParams = layoutParams
//
//                    //4
//                    rocketIcon.rotation = (animatedValue % 360 - 270)
//                }
//                //5
//                anim.duration = if (switch1.isChecked) 2000 else 1000
//
//                //6
//                anim.interpolator = LinearInterpolator()
//                anim.start()


            }
        });

        }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        constraintSet2.clone(this,R.layout.activity_main);
        AutoTransition autoTransition=new AutoTransition();
        autoTransition.setDuration(1000);
        TransitionManager.beginDelayedTransition(mLayout,autoTransition);
        constraintSet2.applyTo(mLayout);
    }
    //    override fun onEnterAnimationComplete() { //1
//        super.onEnterAnimationComplete()
//
//        constraintSet2.clone(this, R.layout.activity_main) //2
//
//        //apply the transition
//        val transition = AutoTransition() //3
//        transition.duration = 1000 //4
//        TransitionManager.beginDelayedTransition(constraintLayout, transition) //5
//
//        constraintSet2.applyTo(constraintLayout) //6
//    }
    }

