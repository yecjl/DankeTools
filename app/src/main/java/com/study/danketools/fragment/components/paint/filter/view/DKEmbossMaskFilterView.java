package com.study.danketools.fragment.components.paint.filter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.study.danketools.R;

public class DKEmbossMaskFilterView extends View {

    private Paint mPaint;
    private int mWidth;
    private Paint mTextPaint;

    public DKEmbossMaskFilterView(Context context) {
        this(context, null);
    }

    public DKEmbossMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKEmbossMaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        // 正常：
        RectF rectF = new RectF(0, 0, rectWidth, rectHeight);
        canvas.drawRect(rectF, mPaint);

        // 绘制文案
        Rect textBounds = new Rect();
        String normalName = "正常";
        mTextPaint.getTextBounds(normalName, 0, normalName.length(), textBounds);
        int textWidth = textBounds.right - textBounds.left;
        canvas.drawText(normalName, (rectWidth - textWidth) / 2, rectHeight + radius, mTextPaint);

        // 设置下一轮绘制
        resetPaint();
        canvas.translate(spacing + rectWidth, 0);

        // 浮雕效果
        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{1, 1, 1}, 0.2f, 60, 80));
        canvas.drawRect(rectF, mPaint);

        // 绘制文案
        normalName = "浮雕遮罩滤镜";
        mTextPaint.getTextBounds(normalName, 0, normalName.length(), textBounds);
        textWidth = textBounds.right - textBounds.left;
        canvas.drawText(normalName, (rectWidth - textWidth) / 2, rectHeight + radius, mTextPaint);
    }

    private void resetPaint() {
        mPaint.reset();
        mPaint.setColor(Color.RED);
    }
}
