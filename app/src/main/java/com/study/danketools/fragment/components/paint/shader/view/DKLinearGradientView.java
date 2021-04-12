package com.study.danketools.fragment.components.paint.shader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DKLinearGradientView extends View {
    private Shader.TileMode tile;
    private Paint mPaint;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private int mWidth;

    public DKLinearGradientView(Context context) {
        this(context, null);
    }

    public DKLinearGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKLinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int drawWidth = mWidth / 3;
        LinearGradient linearGradient = new LinearGradient(0, 0, drawWidth, drawWidth, mColors, null, tile);
        mPaint.setShader(linearGradient);
        canvas.translate(mWidth / 2 - drawWidth, 0);
        canvas.drawRect(0, 0, drawWidth * 2, drawWidth * 2, mPaint);
    }

    public void setBitmapShader(Shader.TileMode tile) {
        this.tile = tile;
        invalidate();
    }
}
