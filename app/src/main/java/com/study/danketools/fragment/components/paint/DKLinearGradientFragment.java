package com.study.danketools.fragment.components.paint;

import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;
import com.study.danketools.R;
import com.study.danketools.base.BaseFragment;
import com.study.danketools.fragment.components.paint.view.DKLinearGradientView;
import com.study.danketools.manager.QDDataManager;
import com.study.danketools.model.QDItemDescription;

import butterknife.BindView;
import butterknife.ButterKnife;

@Widget(name = "LinearGradient线性渲染")
public class DKLinearGradientFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.view)
    DKLinearGradientView view;
    @BindView(R.id.btnCLAMP)
    QMUIRoundButton btnCLAMP;
    @BindView(R.id.btnREPEAT)
    QMUIRoundButton btnREPEAT;
    @BindView(R.id.btnMIRROR)
    QMUIRoundButton btnMIRROR;
    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_linear_gradient, null);
        ButterKnife.bind(this, root);
        mQDItemDescription = QDDataManager.getInstance().getDescription(this.getClass());
        initTopBar();
        initClick();
        return root;
    }

    private void initClick() {
        btnCLAMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBitmapShader(Shader.TileMode.CLAMP);
            }
        });
        btnREPEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBitmapShader(Shader.TileMode.REPEAT);
            }
        });

        btnMIRROR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBitmapShader(Shader.TileMode.MIRROR);
            }
        });
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
