package com.study.danketools.fragment.components.paint.shader.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.study.danketools.R;

import org.jetbrains.annotations.Nullable;

public class DKBitmapShaderView extends View {
    private Paint mPaint;
    private Shader.TileMode tileX;
    private Shader.TileMode tileY;
    private int mWidth;
    private float scale;
    private int shape;
    public static final int SHAPE_RECT = 0;
    public static final int SHAPE_CIRCLE = 1;
    public static final int SHAPE_OVAL = 2;
    private int bmpWidth;
    private int bmpHeight;
    private Bitmap bitmap;

    public DKBitmapShaderView(Context context) {
        this(context, null);
    }

    public DKBitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKBitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        tileX = Shader.TileMode.MIRROR;
        tileY = Shader.TileMode.MIRROR;
        scale = 1;
        shape = SHAPE_RECT;

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_guidao1);
        bmpWidth = bitmap.getWidth();
        bmpHeight = bitmap.getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();

        setMeasuredDimension(
                QMUIViewHelper.getMeasuredDimension(mWidth, widthMeasureSpec, this),
                QMUIViewHelper.getMeasuredDimension(bmpHeight * 2, heightMeasureSpec, this));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        BitmapShader shader = new BitmapShader(bitmap, tileX, tileY);
        mPaint.setShader(shader);
        mPaint.setAntiAlias(true);

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        shader.setLocalMatrix(matrix);
        switch (shape) {
            case SHAPE_RECT:
                canvas.drawRect(new Rect(0, 0, mWidth, bmpHeight * 2), mPaint);
                break;
            case SHAPE_CIRCLE:
                canvas.drawCircle(mWidth / 2, bmpHeight, Math.min(mWidth, bmpHeight * 2) / 2, mPaint);
                break;
            case SHAPE_OVAL:
                canvas.drawOval(new RectF(0, 0, mWidth, bmpHeight * 2), mPaint);
                break;
        }
    }

    public void setBitmapShader(Shader.TileMode tileX, Shader.TileMode tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
        invalidate();
    }

    public void setScale(float scale) {
        this.scale = scale;
        invalidate();
    }

    public void setShape(int shape) {
        this.shape = shape;
    }
}