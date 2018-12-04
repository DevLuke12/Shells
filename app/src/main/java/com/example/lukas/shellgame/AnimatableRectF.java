package com.example.lukas.shellgame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;




public class AnimatableRectF extends RectF
{
    public AnimatableRectF() {
        super();
    }

    public AnimatableRectF(float left, float top, float right, float bottom) {
        super(left, top, right, bottom);
    }

    public AnimatableRectF(RectF r) {
        super(r);
    }

    public AnimatableRectF(Rect r) {
        super(r);
    }

    public void setTop(float top){
        this.top = top;
    }
    public void setBottom(float bottom){
        this.bottom = bottom;
    }
    public void setRight(float right){
        this.right = right;
    }
    public void setLeft(float left){
        this.left = left;
    }

    public void ShowSelectedShell(AnimatableRectF rectF)
    {

        Path path = new Path();
        path.lineTo(200f,300f);
        ObjectAnimator animateBottom = ObjectAnimator.ofFloat(rectF, "top", rectF.top, rectF.top+50);
        animateBottom.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //invalidate();
                //postInvalidate();
            }
        });
        AnimatorSet rectAnimation = new AnimatorSet();
        rectAnimation.playTogether(animateBottom);
        rectAnimation.setDuration(1000).start();
    }
}
