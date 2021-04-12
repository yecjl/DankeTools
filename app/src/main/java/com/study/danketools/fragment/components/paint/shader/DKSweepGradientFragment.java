package com.study.danketools.fragment.components.paint.shader;

import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;
import com.study.danketools.R;
import com.study.danketools.base.BaseFragment;
import com.study.danketools.fragment.components.paint.shader.view.DKSweepGradientView;
import com.study.danketools.manager.QDDataManager;
import com.study.danketools.model.QDItemDescription;

import butterknife.BindView;
import butterknife.ButterKnife;

@Widget(name = "SweepGradient扫描渲染")
public class DKSweepGradientFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.view)
    DKSweepGradientView view;
    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_sweep_gradient, null);
        ButterKnife.bind(this, root);
        mQDItemDescription = QDDataManager.getInstance().getDescription(this.getClass());
        initTopBar();
        return root;
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
