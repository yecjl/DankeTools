package com.study.danketools.fragment.components.paint.shader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.study.danketools.R;

public class DKLinearGradientTextView extends AppCompatTextView {

    private int mViewWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private float mTranslate = 0;
    private float delta = 15;

    public DKLinearGradientTextView(@NonNull Context context) {
        this(context, null);
    }

    public DKLinearGradientTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DKLinearGradientTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                String text = getText().toString();
                int length = text.length();
                int size;
                if (length > 0) {
                    // mViewWidth/字体总数, 就得到了每个字的像素, 然后 *3 表示3个文字的像素
                    size = mViewWidth / length * 3;
                } else {
                    size = mViewWidth;
                }
                mLinearGradient = new LinearGradient(-size, 0, 0, 0,
                        new int[]{getResources().getColor(R.color.qmui_config_color_black), getResources().getColor(R.color.app_color_theme_5), getResources().getColor(R.color.qmui_config_color_black)},
                        new float[]{0, 0.5f, 1},
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float mTextWidth = getPaint().measureText(getText().toString());
        mTranslate += delta;
        /**
         * 如果位置已经移动到了整方了那个文字的地就开始往回滚动。
         * 但是如果小于1 了那么又开始递增，走另外一个逻辑
         */
        if (mTranslate > mTextWidth + 1 || mTranslate < 1) {
            delta = -delta;
        }

        mGradientMatrix.setTranslate(mTranslate, 0 );
        mLinearGradient.setLocalMatrix(mGradientMatrix);

        // paint是textView的所以只需要不断色控制画笔的shader  然后利用矩阵控制位移即可
        postInvalidateDelayed(30);
    }
}
