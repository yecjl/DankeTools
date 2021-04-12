package com.study.danketools.fragment.components.paint.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.study.danketools.R;

/**
 * 放大镜
 */
public class DKBitmapShaderZoomImageView extends View {

    // 放大倍数
    private static final int FACTOR = 2;
    // 放大镜的半径
    private static final int RADIUS = 75;
    // 原图
    private Bitmap mBitmap;
    // 放大后的图
    private Bitmap mBitmapScale;
    // 制作的圆形的图片（放大的局部），盖在Canvas上面
    private ShapeDrawable mShapeDrawable;
    private Matrix mMatrix;
    private int bmpWidth;
    private int bmpHeight;

    public DKBitmapShaderZoomImageView(Context context) {
        this(context, null);
    }

    public DKBitmapShaderZoomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKBitmapShaderZoomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_guidao2);
        mBitmapScale = mBitmap;
        bmpWidth = mBitmap.getWidth();
        bmpHeight = mBitmap.getHeight();

        // 放大后的整个图片
        mBitmapScale = Bitmap.createScaledBitmap(mBitmapScale, mBitmapScale.getWidth() * FACTOR, mBitmapScale.getHeight() * FACTOR, true);
        BitmapShader bitmapShader = new BitmapShader(mBitmapScale, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mShapeDrawable = new ShapeDrawable(new OvalShape());
        mShapeDrawable.getPaint().setShader(bitmapShader);
        // 切出矩形区域，用来画圆（内切圆）初始化画个圆
//        mShapeDrawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2);
        mMatrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(
                QMUIViewHelper.getMeasuredDimension(bmpWidth, widthMeasureSpec, this),
                QMUIViewHelper.getMeasuredDimension(bmpHeight, heightMeasureSpec, this));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 1、画原图
        canvas.drawBitmap(mBitmap, 0, 0, null);
        // 2、画放大镜的图
        mShapeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);

        int x = (int) event.getX();
        int y = (int) event.getY();

        // 将放大的图片往相反的方向挪动
        mMatrix.setTranslate(RADIUS - x * FACTOR, RADIUS - y * FACTOR);
        mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
        // 切出手势区域点位置的圆
        mShapeDrawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
        invalidate();
        return true;
    }
}
