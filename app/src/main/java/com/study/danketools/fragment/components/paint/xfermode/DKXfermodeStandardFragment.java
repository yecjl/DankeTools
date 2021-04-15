package com.study.danketools.fragment.components.paint.xfermode;

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

@Widget(name = "Xfermode 标准效果")
public class DKXfermodeStandardFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_xfermode_standard, null);
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
