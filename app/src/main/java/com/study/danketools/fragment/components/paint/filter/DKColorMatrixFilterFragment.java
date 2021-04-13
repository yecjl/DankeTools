package com.study.danketools.fragment.components.paint.filter;

import android.graphics.ColorMatrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;
import com.study.danketools.R;
import com.study.danketools.base.BaseFragment;
import com.study.danketools.fragment.components.paint.filter.view.DKColorMatrixFilterView;
import com.study.danketools.manager.QDDataManager;
import com.study.danketools.model.QDItemDescription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Widget(name = "颜色矩阵设置滤镜")
public class DKColorMatrixFilterFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.listview)
    ListView listview;
    private QDItemDescription mQDItemDescription;
    private List<DKColorMatrixFilterView.MyColorMatrix> mColorMatrixList;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_color_matrix_filter, null);
        ButterKnife.bind(this, root);
        mQDItemDescription = QDDataManager.getInstance().getDescription(this.getClass());
        initTopBar();
        initColorMatrixList();
        initListView();
        return root;
    }

    private void initColorMatrixList() {
        mColorMatrixList = new ArrayList<>();
        mColorMatrixList.add(new DKColorMatrixFilterView.MyColorMatrix("原始图片", new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        })));
        mColorMatrixList.add(new DKColorMatrixFilterView.MyColorMatrix("缩放运算---乘法--颜色增强", new ColorMatrix(new float[]{
                1.2f, 0, 0, 0, 0,
                0, 1.2f, 0, 0, 0,
                0, 0, 1.2f, 0, 0,
                0, 0, 0, 1.2f, 0
        })));
        mColorMatrixList.add(new DKColorMatrixFilterView.MyColorMatrix("平移运算---加法", new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 100,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        })));
        mColorMatrixList.add(new DKColorMatrixFilterView.MyColorMatrix("反相效果--底片效果", new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        })));
        /** 黑白照片
         *是将我们的三通道变为单通道的灰度模式
         *去色原理：只要把R G B 三通道的色彩信息设置成一样，那么图像就会变成灰色，
         *同时为了保证图像亮度不变，同一个通道里的R+G+B =1
         */
        mColorMatrixList.add(new DKColorMatrixFilterView.MyColorMatrix("黑白照片", new ColorMatrix(new float[]{
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0.213f, 0.715f, 0.072f, 0, 0,
                0, 0, 0, 1, 0,
        })));
        mColorMatrixList.add(new DKColorMatrixFilterView.MyColorMatrix("发色效果---（比如红色和绿色交换）", new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 0, 0.5F, 0,
        })));
        mColorMatrixList.add(new DKColorMatrixFilterView.MyColorMatrix("复古效果", new ColorMatrix(new float[]{
                1 / 2f, 1 / 2f, 1 / 2f, 0, 0,
                1 / 3f, 1 / 3f, 1 / 3f, 0, 0,
                1 / 4f, 1 / 4f, 1 / 4f, 0, 0,
                0, 0, 0, 1, 0,
        })));

    }

    private void initListView() {
        listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mColorMatrixList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                DKColorMatrixFilterView view;
                if (convertView == null) {
                    view = new DKColorMatrixFilterView(getContext());
                } else {
                    view = (DKColorMatrixFilterView) convertView;
                }
                view.setColorMatrix(mColorMatrixList.get(position));
                return view;
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
