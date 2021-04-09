package com.study.danketools.fragment.components.paint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DKSweepGradientView extends View {
    private Paint mPaint;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private int mWidth;
    private int mHeight;

    public DKSweepGradientView(Context context) {
        this(context, null);
    }

    public DKSweepGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKSweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = mWidth / 3;
        int cx = mWidth / 2;
        int cy = mHeight / 2;
        SweepGradient sweepGradient = new SweepGradient(cx, cy, mColors, null);
        mPaint.setShader(sweepGradient);
        canvas.drawCircle(cx, cy, radius, mPaint);
    }

}
