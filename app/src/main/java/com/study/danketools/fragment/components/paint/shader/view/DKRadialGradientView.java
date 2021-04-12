package com.study.danketools.fragment.components.paint.shader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DKRadialGradientView extends View {
    private Paint mPaint;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private int mWidth;
    private int mHeight;
    private Shader.TileMode tile;

    public DKRadialGradientView(Context context) {
        this(context, null);
    }

    public DKRadialGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKRadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        tile = Shader.TileMode.MIRROR;
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
        int cy = radius;
        RadialGradient radialGradient = new RadialGradient(cx, cy, radius / 2, mColors, null, tile);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(cx, cy, radius, mPaint);
    }

    public void setBitmapShader(Shader.TileMode tile) {
        this.tile = tile;
        invalidate();
    }

}
