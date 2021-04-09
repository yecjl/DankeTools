package com.study.danketools.fragment.components.paint.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.study.danketools.R;

public class DKComposeShaderView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public DKComposeShaderView(Context context) {
        this(context, null);
    }

    public DKComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        // 创建BitmapShader，用以绘制心
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_compose_shard_heart);
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // 创建LinearGradient，用以产生从左上角到右下角的颜色渐变效果
        LinearGradient linearGradient = new LinearGradient(0, 0, bmpWidth, bmpHeight, getResources().getColor(R.color.app_color_theme_9),  getResources().getColor(R.color.app_color_theme_3), Shader.TileMode.CLAMP);
        //bitmapShader对应目标像素，linearGradient对应源像素，像素颜色混合采用MULTIPLY模式
        ComposeShader composeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.MULTIPLY);
        mPaint.setShader(composeShader);
        canvas.translate(mWidth / 2 - bmpWidth, 0);
        canvas.drawRect(0, 0, bmpWidth, bmpHeight, mPaint);
    }
}
