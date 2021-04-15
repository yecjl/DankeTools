package com.study.danketools.fragment.components.paint.xfermode.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.study.danketools.R;

public class DKXfermodeView extends View {
    Paint mPaint;

    float mItemSize = 0;

    float mItemHorizontalOffset = 0;
    float mItemVerticalOffset = 0;

    float mCircleRadius = 0;
    float mRectSize = 0;

    private int mCircleColor = 0xffE91E63; // 枚红色
    private int mRectColor = 0xff2196F3; // 蓝色
    private int mTextColor = 0xff1A73E8; // 文字颜色

    private static final PorterDuff.Mode[] sModes = {
            PorterDuff.Mode.SRC,
            PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.SRC_IN,
            PorterDuff.Mode.SRC_ATOP,
            PorterDuff.Mode.DST,
            PorterDuff.Mode.DST_OVER,
            PorterDuff.Mode.DST_IN,
            PorterDuff.Mode.DST_ATOP,
            PorterDuff.Mode.CLEAR,
            PorterDuff.Mode.SRC_OUT,
            PorterDuff.Mode.DST_OUT,
            PorterDuff.Mode.XOR,
            PorterDuff.Mode.DARKEN,
            PorterDuff.Mode.LIGHTEN,
            PorterDuff.Mode.MULTIPLY,
            PorterDuff.Mode.SCREEN,
            PorterDuff.Mode.ADD,
            PorterDuff.Mode.OVERLAY,
    };
    private Bitmap mBtmBg;
    private float mBtmBgWidth;
    private float mBtmBgHeight;
    private float mSpacing;
    private Paint mTextPaint;
    private int mColumn = 4;

    public DKXfermodeView(Context context) {
        this(context, null);
    }

    public DKXfermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.qmui_btn_text_14size));

        mBtmBg = BitmapFactory.decodeResource(getResources(), R.mipmap.img_composite_clear);
        mBtmBgWidth = mBtmBg.getWidth();
        mBtmBgHeight = mBtmBg.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mItemSize = w / 4.5f;
        mCircleRadius = mItemSize / 3;
        mRectSize = mItemSize * 0.6f;
        mSpacing = (mBtmBgWidth - mRectSize - mCircleRadius) / 2;
        mItemVerticalOffset = mItemSize * 0.426f;
        mItemHorizontalOffset = (w - mBtmBgWidth * mColumn) / (mColumn + 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        float dxSum = mItemHorizontalOffset;
        canvas.translate(dxSum, dxSum);

        for (int i = 0; i < sModes.length; i++) {
            mPaint.setXfermode(null);
            canvas.save();

            // 绘制背景
            canvas.drawBitmap(mBtmBg, 0, 0, mPaint);

            // 绘制文字
            String name = sModes[i].name();
            mTextPaint.setColor(mTextColor);
            Rect textBounds = new Rect();
            mTextPaint.getTextBounds(name, 0, name.length(), textBounds);
            float textWidth = textBounds.right - textBounds.left;
            float textHeight = textBounds.bottom - textBounds.top;
            float drawTextY = mBtmBgHeight + mSpacing * 2 + textHeight;
            canvas.drawText(name, (mBtmBgWidth - textWidth) / 2, drawTextY, mTextPaint);
            mPaint.setColor(mTextColor);

            int layer = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);

            // 画圆
            mPaint.setColor(mCircleColor);
            canvas.drawCircle(mSpacing + mRectSize, mSpacing + mCircleRadius, mCircleRadius, mPaint);

            // choose a mode
            mPaint.setXfermode(new PorterDuffXfermode(sModes[i]));

            // 画矩形
            mPaint.setColor(mRectColor);
            canvas.drawRect(mSpacing, mSpacing + mCircleRadius, mRectSize + mSpacing, mRectSize + mCircleRadius + mSpacing, mPaint);

            canvas.restoreToCount(layer);

            float dx = mBtmBgWidth + mItemHorizontalOffset;
            float dy = 0;
            if (i % mColumn == mColumn - 1) {
                dy += drawTextY + mItemHorizontalOffset;
                dx = -dxSum + mItemHorizontalOffset;
                dxSum = mItemHorizontalOffset;
            } else {
                dxSum += dx;
            }
            canvas.translate(dx, dy);
        }
    }
}
