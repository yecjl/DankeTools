package com.study.danketools.fragment.components;

import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;
import com.study.danketools.R;
import com.study.danketools.base.BaseFragment;
import com.study.danketools.fragment.components.paint.filter.DKBlurMaskFilterFragment;
import com.study.danketools.fragment.components.paint.filter.DKColorMatrixFilterFragment;
import com.study.danketools.fragment.components.paint.filter.DKEmbossMaskFilterFragment;
import com.study.danketools.fragment.components.paint.shader.DKBitmapShaderFragment;
import com.study.danketools.fragment.components.paint.shader.DKComposeShaderFragment;
import com.study.danketools.fragment.components.paint.shader.DKLinearGradientFragment;
import com.study.danketools.fragment.components.paint.shader.DKRadialGradientFragment;
import com.study.danketools.fragment.components.paint.shader.DKSweepGradientFragment;
import com.study.danketools.manager.QDDataManager;
import com.study.danketools.model.QDItemDescription;

import butterknife.BindView;
import butterknife.ButterKnife;

@Widget(name = "Paint", iconRes = R.mipmap.icon_grid_group_list_view)
public class DKPaintFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.groupListView)
    QMUIGroupListView mGroupListView;
    private QDItemDescription mQDItemDescription;
    private QDDataManager mQDDataManager;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_paint, null);
        ButterKnife.bind(this, root);
        mQDDataManager = QDDataManager.getInstance();
        mQDItemDescription = mQDDataManager.getDescription(this.getClass());
        initTopBar();
        initGroupListView();
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

    private void initGroupListView() {
        QMUICommonListItemView bitmapShader = mGroupListView.createItemView(mQDDataManager.getName(DKBitmapShaderFragment.class));
        bitmapShader.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView linearGradient = mGroupListView.createItemView(mQDDataManager.getName(DKLinearGradientFragment.class));
        linearGradient.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView sweepGradient = mGroupListView.createItemView(mQDDataManager.getName(DKSweepGradientFragment.class));
        sweepGradient.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView radialGradient = mGroupListView.createItemView(mQDDataManager.getName(DKRadialGradientFragment.class));
        radialGradient.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView composeShader = mGroupListView.createItemView(mQDDataManager.getName(DKComposeShaderFragment.class));
        composeShader.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView blurMaskFilter = mGroupListView.createItemView(mQDDataManager.getName(DKBlurMaskFilterFragment.class));
        blurMaskFilter.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView embossMaskFilter = mGroupListView.createItemView(mQDDataManager.getName(DKEmbossMaskFilterFragment.class));
        embossMaskFilter.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView colorMatrixFilter = mGroupListView.createItemView(mQDDataManager.getName(DKColorMatrixFilterFragment.class));
        colorMatrixFilter.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    if (v == bitmapShader) {
                        DKBitmapShaderFragment bitmapShaderFragment = new DKBitmapShaderFragment();
                        startFragment(bitmapShaderFragment);
                    } else if (v == linearGradient) {
                        DKLinearGradientFragment linearGradientFragment = new DKLinearGradientFragment();
                        startFragment(linearGradientFragment);
                    } else if (v == sweepGradient) {
                        DKSweepGradientFragment sweepGradientFragment = new DKSweepGradientFragment();
                        startFragment(sweepGradientFragment);
                    } else if (v == radialGradient) {
                        DKRadialGradientFragment radialGradientFragment = new DKRadialGradientFragment();
                        startFragment(radialGradientFragment);
                    } else if (v == composeShader) {
                        DKComposeShaderFragment composeShaderFragment = new DKComposeShaderFragment();
                        startFragment(composeShaderFragment);
                    } else if (v == blurMaskFilter) {
                        DKBlurMaskFilterFragment maskFilterFragment = new DKBlurMaskFilterFragment();
                        startFragment(maskFilterFragment);
                    } else if (v == embossMaskFilter) {
                        DKEmbossMaskFilterFragment embossMaskFilterFragment = new DKEmbossMaskFilterFragment();
                        startFragment(embossMaskFilterFragment);
                    } else if (v == colorMatrixFilter) {
                        DKColorMatrixFilterFragment colorMatrixFilterFragment = new DKColorMatrixFilterFragment();
                        startFragment(colorMatrixFilterFragment);
                    }
                }
            }
        };

        QMUIGroupListView.newSection(getContext()).setTitle("1、渲染")
                .addItemView(bitmapShader, onClickListener)
                .addItemView(linearGradient, onClickListener)
                .addItemView(sweepGradient, onClickListener)
                .addItemView(radialGradient, onClickListener)
                .addItemView(composeShader, onClickListener)
                .addTo(mGroupListView);

        QMUIGroupListView.newSection(getContext()).setTitle("2、滤镜")
                .addItemView(blurMaskFilter, onClickListener)
                .addItemView(embossMaskFilter, onClickListener)
                .addItemView(colorMatrixFilter, onClickListener)
                .addTo(mGroupListView);

        QMUIGroupListView.newSection(getContext()).setTitle("3、Xfermode")
                .addTo(mGroupListView);
    }
}
