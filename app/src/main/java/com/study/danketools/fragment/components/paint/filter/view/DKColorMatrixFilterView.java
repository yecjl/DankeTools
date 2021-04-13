package com.study.danketools.fragment.components.paint.filter.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.study.danketools.R;

import java.util.List;

public class DKColorMatrixFilterView extends View {

    private Paint mPaint;
    private int mWidth;
    private Paint mTextPaint;
    private Bitmap mBitmap;
    private int btmWidth;
    private int btmHeight;
    private int rectWidth;
    private int rectHeight;
    private int leftSpacing;
    private int heightSpacing;
    private MyColorMatrix mColorMatrix;

    public DKColorMatrixFilterView(Context context) {
        this(context, null);
    }

    public DKColorMatrixFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKColorMatrixFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStrokeWidth(10);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.qmui_btn_text_size));
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_guidao2);
        btmWidth = mBitmap.getWidth();
        btmHeight = mBitmap.getHeight();
        rectWidth = btmWidth;
        rectHeight = btmHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        leftSpacing = (mWidth - rectWidth) / 2;
        heightSpacing = mWidth / 6;

        setMeasuredDimension(mWidth, heightSpacing + rectHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 关闭单个View的硬件加速功能
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        canvas.translate(leftSpacing, 0); // 图片居中

        MyColorMatrix myColorMatrix = mColorMatrix;
        if (myColorMatrix != null) {
            String name = myColorMatrix.name;
            ColorMatrix colorMatrix = myColorMatrix.colorMatrix;
            // 绘制滤镜
            RectF rectF = new RectF(0, 0, rectWidth, rectHeight);
            mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(mBitmap, null, rectF, mPaint);

            // 绘制文案
            Rect textBounds = new Rect();
            mTextPaint.getTextBounds(name, 0, name.length(), textBounds);
            int textWidth = textBounds.right - textBounds.left;
            int textHeight = textBounds.bottom - textBounds.top;
            canvas.drawText(name, (rectWidth - textWidth) / 2, rectHeight + textHeight * 1.5f, mTextPaint);
        }
    }

    public void setColorMatrix(MyColorMatrix colorMatrix) {
        this.mColorMatrix = colorMatrix;
    }

    public static class MyColorMatrix {
        private String name;
        private ColorMatrix colorMatrix;

        public MyColorMatrix(String name, ColorMatrix colorMatrix) {
            this.name = name;
            this.colorMatrix = colorMatrix;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ColorMatrix getColorMatrix() {
            return colorMatrix;
        }

        public void setColorMatrix(ColorMatrix colorMatrix) {
            this.colorMatrix = colorMatrix;
        }
    }

}
