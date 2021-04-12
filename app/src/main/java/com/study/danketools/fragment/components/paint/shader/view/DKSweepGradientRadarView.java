package com.study.danketools.fragment.components.paint.shader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DKSweepGradientRadarView extends View {

    private Paint mPaintCircle;
    private Paint mPaintRadar;
    private int scanSpeed = 5; // 扫描速度
    private int mWidth;
    //五个圆
    private float[] pots = {0.05f, 0.1f, 0.15f, 0.2f, 0.25f};
    private SweepGradient scanShader;
    private Matrix matrix = new Matrix(); // 旋转需要的矩阵
    private float radius;
    private float cx;
    private float cy;

    public DKSweepGradientRadarView(Context context) {
        this(context, null);
    }

    public DKSweepGradientRadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKSweepGradientRadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 画圆用到的paint
        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE); // 描边
        mPaintCircle.setStrokeWidth(1);
        mPaintCircle.setAlpha(100); // 透明度
        mPaintCircle.setAntiAlias(true);  // 抗锯齿
        mPaintCircle.setColor(Color.parseColor("#B0C4DE")); // 设置颜色 亮钢兰色

        // 扫描用到的paint
        mPaintRadar = new Paint();
        mPaintRadar.setStyle(Paint.Style.FILL_AND_STROKE); // 填充
        mPaintRadar.setAntiAlias(true); // 抗锯齿

        post(mRun);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        radius = mWidth * pots[pots.length - 1];
        cx = mWidth / 2;
        cy = radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < pots.length; i++) {
            canvas.drawCircle(cx, cy, mWidth * pots[i], mPaintCircle);
        }

        // 画布的旋转变换 需要调用save() 和 restore()
        canvas.save();
        scanShader = new SweepGradient(cx, cy, new int[]{Color.TRANSPARENT, Color.parseColor("#84B5CA")}, null);
        mPaintRadar.setShader(scanShader);  // 设置着色器
        canvas.concat(matrix);
        canvas.drawCircle(cx, cy, radius, mPaintRadar);
        canvas.restore();
    }

    private Runnable mRun = new Runnable() {
        @Override
        public void run() {
            matrix.postRotate(scanSpeed, cx, cy); // 旋转矩阵
            invalidate(); // 通知view重绘
            postDelayed(mRun, 50);  // 调用自身 重复绘制
        }
    };

}
