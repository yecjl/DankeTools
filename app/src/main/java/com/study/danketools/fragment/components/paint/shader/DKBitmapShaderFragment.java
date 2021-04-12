package com.study.danketools.fragment.components.paint.shader;

import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;
import com.study.danketools.R;
import com.study.danketools.base.BaseFragment;
import com.study.danketools.fragment.components.paint.shader.view.DKBitmapShaderView;
import com.study.danketools.manager.QDDataManager;
import com.study.danketools.model.QDItemDescription;

import butterknife.BindView;
import butterknife.ButterKnife;

@Widget(name = "BitmapShader位图渲染")
public class DKBitmapShaderFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.btnCLAMP)
    QMUIRoundButton btnCLAMP;
    @BindView(R.id.btnREPEAT)
    QMUIRoundButton btnREPEAT;
    @BindView(R.id.btnMIRROR)
    QMUIRoundButton btnMIRROR;
    @BindView(R.id.iv_img)
    DKBitmapShaderView ivImg;
    @BindView(R.id.test_seekbar_scale)
    SeekBar testSeekbarScale;
    @BindView(R.id.iv_img_circle)
    DKBitmapShaderView ivImgCircle;
    @BindView(R.id.iv_img_oval)
    DKBitmapShaderView ivImgOval;
    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bitmap_shader, null);
        ButterKnife.bind(this, root);
        mQDItemDescription = QDDataManager.getInstance().getDescription(this.getClass());
        initTopBar();
        initClick();
        initBitmapShaderView();
        return root;
    }

    private void initBitmapShaderView() {
        ivImgCircle.setShape(DKBitmapShaderView.SHAPE_CIRCLE);
        ivImgOval.setShape(DKBitmapShaderView.SHAPE_OVAL);
    }

    private void initClick() {
        btnCLAMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBitmapShader(Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
        });
        btnREPEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBitmapShader(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            }
        });

        btnMIRROR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBitmapShader(Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
            }
        });
        testSeekbarScale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = progress * 1f / 100;
                ivImg.setScale(scale);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void getBitmapShader(Shader.TileMode tileX, Shader.TileMode tileY) {
        ivImg.setBitmapShader(tileX, tileY);
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopBar.setTitle(mQDItemDescription.getName());
    }

}
