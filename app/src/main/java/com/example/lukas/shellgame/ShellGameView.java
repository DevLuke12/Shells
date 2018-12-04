package com.example.lukas.shellgame;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class ShellGameView extends View
{
    private Paint paint;
    private AnimatableRectF firstShell;
    private AnimatableRectF secondShell;
    private AnimatableRectF thirdShell;
    private int locationFirstShell = 1;
    private int locationSecondShell = 2;
    private int locationThirdShell = 3;
    private float selectedX = 0;
    private float selectedY = 0;

    public ShellGameView(Context context)
    {
        super(context);
        Init(context);
    }

    public ShellGameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Init(context);
    }

    public ShellGameView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        Init(context);
    }

    private void Init(Context context)
    {
        paint = new Paint();
        firstShell = new AnimatableRectF(400, 200, 500, 350);
        secondShell = new AnimatableRectF(600, 200, 700, 350);
        thirdShell = new AnimatableRectF(800, 200, 900, 350);
        //Point p = new Point();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        paint.setColor(Color.rgb(213,145,89));
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawOval(firstShell, paint);
        canvas.drawOval(secondShell, paint);
        canvas.drawOval(thirdShell, paint);



    }

    private String GetSelectedShell(AnimatableRectF firstShell, AnimatableRectF secondShell, AnimatableRectF thirdShell,
                                             float touchX, float touchY)
    {
        if (firstShell.contains(touchX, touchY))
            return "firstShell";
        else if (secondShell.contains(touchX, touchY))
            return "secondShell";
        else if(thirdShell.contains(touchX, touchY))
            return "thirdShell";
        else
            return "You do not select shell";
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {


        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                selectedX = event.getX();
                selectedY = event.getY();
                Log.d("DownX", String.valueOf(selectedX));
                Log.d("DownY", String.valueOf(selectedY));
                break;
            case MotionEvent.ACTION_UP:
                String shellString = GetSelectedShell(firstShell, secondShell, thirdShell, selectedX, selectedY);
                Log.d("UpX", String.valueOf(selectedX));
                Log.d("UpY", String.valueOf(selectedY));
                Log.d("shell", shellString);
                if(shellString == "secondShell")
                {
                    AnimateShell(0,-150,secondShell,2000);
                }
                default:
                    return false;
        }
        return true;
    }

    private void AnimateShell(float newX, float newY, AnimatableRectF rectF, int milisecondDuration)
    {

        ObjectAnimator animateLeft = ObjectAnimator.ofFloat(rectF, "left", rectF.left, rectF.left + newX);
        ObjectAnimator animateRight = ObjectAnimator.ofFloat(rectF, "right", rectF.right, rectF.right + newX);
        ObjectAnimator animateTop = ObjectAnimator.ofFloat(rectF, "top", rectF.top, rectF.top + newY);
        ObjectAnimator animateBottom = ObjectAnimator.ofFloat(rectF, "bottom", rectF.bottom, rectF.bottom + newY);
        animateBottom.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                invalidate();
            }
        });

        AnimatorSet rectAnimation = new AnimatorSet();
        rectAnimation.playTogether(animateLeft, animateRight, animateTop, animateBottom);
        rectAnimation.setDuration(milisecondDuration).start();
    }

}
