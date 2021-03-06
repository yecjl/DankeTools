/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.study.danketools.fragment.components;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;
import com.study.danketools.R;
import com.study.danketools.base.BaseFragment;
import com.study.danketools.manager.QDDataManager;
import com.study.danketools.model.QDItemDescription;

import butterknife.BindView;
import butterknife.ButterKnife;

@Widget(widgetClass = QMUIFloatLayout.class, iconRes = R.mipmap.icon_grid_float_layout)
public class QDFloatLayoutFragment extends BaseFragment {
    private static final String TAG = "QDFloatLayoutFragment";

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.qmuidemo_floatlayout)
    QMUIFloatLayout mFloatLayout;

    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_floatlayout, null);
        ButterKnife.bind(this, root);

        mQDItemDescription = QDDataManager.getInstance().getDescription(this.getClass());
        initTopBar();
        for (int i = 0; i < 8; i++) {
            addItemToFloatLayout(mFloatLayout);
        }
        mFloatLayout.setOnLineCountChangeListener(new QMUIFloatLayout.OnLineCountChangeListener() {
            @Override
            public void onChange(int oldLineCount, int newLineCount) {
                Log.i(TAG, "oldLineCount = " + oldLineCount + " ;newLineCount = " + newLineCount);
            }
        });
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

        mTopBar.addRightImageButton(R.mipmap.icon_topbar_overflow, R.id.topbar_right_change_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showBottomSheet();
                    }
                });

    }

    private void addItemToFloatLayout(QMUIFloatLayout floatLayout) {
        int currentChildCount = floatLayout.getChildCount();

        TextView textView = new TextView(getActivity());
        int textViewPadding = QMUIDisplayHelper.dp2px(getContext(), 4);
        textView.setPadding(textViewPadding, textViewPadding, textViewPadding, textViewPadding);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.qmui_config_color_white));
        textView.setText(String.valueOf(currentChildCount));
        textView.setBackgroundResource(currentChildCount % 2 == 0 ? R.color.app_color_theme_3 : R.color.app_color_theme_6);

        int textViewSize = QMUIDisplayHelper.dpToPx(60);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(textViewSize, textViewSize);
        floatLayout.addView(textView, lp);
    }

    private void removeItemFromFloatLayout(QMUIFloatLayout floatLayout) {
        if (floatLayout.getChildCount() == 0) {
            return;
        }
        floatLayout.removeView(floatLayout.getChildAt(floatLayout.getChildCount() - 1));
    }

    private void showBottomSheet() {
        new QMUIBottomSheet.BottomListSheetBuilder(getContext())
                .addItem("????????????item")
                .addItem("????????????item")
                .addItem("??????")
                .addItem("??????")
                .addItem("??????")
                .addItem("??????????????????1???")
                .addItem("??????????????????4???item")
                .addItem("????????????????????????")
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        switch (position) {
                            case 0:
                                addItemToFloatLayout(mFloatLayout);
                                break;
                            case 1:
                                removeItemFromFloatLayout(mFloatLayout);
                                break;
                            case 2:
                                mFloatLayout.setGravity(Gravity.LEFT);
                                break;
                            case 3:
                                mFloatLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                                break;
                            case 4:
                                mFloatLayout.setGravity(Gravity.RIGHT);
                                break;
                            case 5:
                                mFloatLayout.setMaxLines(1);
                                break;
                            case 6:
                                mFloatLayout.setMaxNumber(4);
                                break;
                            case 7:
                                mFloatLayout.setMaxLines(Integer.MAX_VALUE);
                                break;
                            default:
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .build().show();
    }

}
