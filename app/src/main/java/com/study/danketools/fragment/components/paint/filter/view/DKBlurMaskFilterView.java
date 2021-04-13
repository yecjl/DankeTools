package com.study.danketools.fragment.components.paint.filter.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.study.danketools.R;

public class DKBlurMaskFilterView extends View {

    private Paint mPaint;
    private int mWidth;
    private Paint mTextPaint;

    public DKBlurMaskFilterView(Context context) {
        this(context, null);
    }

    public DKBlurMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKBlurMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStrokeWidth(10);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.qmui_btn_text_size));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 关闭单个View的硬件加速功能
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        int rectWidth = 200;
        int rectHeight = 200;
        int radius = 50;
        int line = 2;
        int spacing = (mWidth - rectWidth * line) / 3;

        resetPaint();
        canvas.translate(spacing, spacing);

        BlurMaskFilter.Blur[] values = BlurMaskFilter.Blur.values();
        int valueLength = values.length;
        for (int i = 0; i < valueLength; i++) {
            BlurMaskFilter.Blur blur = values[i];
            String name = blur.name();

            // 绘制 BlurMaskFilter.Blur
            RectF rectF = new RectF(0, 0, rectWidth, rectHeight);
            mPaint.setMaskFilter(new BlurMaskFilter(radius, blur));
            canvas.drawRect(rectF, mPaint);

            // 绘制文案
            Rect textBounds = new Rect();
            mTextPaint.getTextBounds(name, 0, name.length(), textBounds);
            int textWidth = textBounds.right - textBounds.left;
            int textHeight = textBounds.bottom - textBounds.top;
            canvas.drawText(name, (rectWidth - textWidth) / 2, rectHeight + radius, mTextPaint);

            // 设置下一轮绘制
            resetPaint();

            int dy = 0;
            int dx = spacing + rectWidth;
            if (i % line == 1) {
                dy = spacing + rectHeight + textHeight;
                dx = -(spacing + rectHeight);
            }
            canvas.translate(dx, dy);
        }
    }

    private void resetPaint() {
        mPaint.reset();
        mPaint.setColor(Color.RED);
    }
}
