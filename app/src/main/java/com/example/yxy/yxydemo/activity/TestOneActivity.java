package com.example.yxy.yxydemo.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.example.yxy.yxydemo.BaseActivity;
import com.example.yxy.yxydemo.MyClassTest.CustomImageSpan;
import com.example.yxy.yxydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1. 使用drawableLeft等属性设置，这种方式对应的java方法是
 * setCompoundDrawablesWithIntrinsicBounds(leftDrawble,topDrawable,rightDrawable,bottomDrawable);
 * 2. 使用 SpannableString ,先将图片转成ImageSpan对象，
 * 然后通过setSpan插入到SpannableString 中，最后再将SpannableString通过setText设置给TextView。
 * （SpannableString 继承自CharSquence）
 * 3. 此外，还有一种利用Html.ImageGetter格式化图片的方式。
 */

public class TestOneActivity extends BaseActivity {

    @Bind(R.id.tv_one)
    TextView tvOne;
    @Bind(R.id.tv_two)
    TextView tvTwo;
    @Bind(R.id.tv_three)
    TextView tvThree;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        super.setContentLayout(savedInstanceState);
        setTopBar(R.layout.activity_test_one, "demo1");
    }

    @Override
    public void initView() {
        super.initView();
        SpannableString spannableString = new SpannableString("点击 按钮 有惊喜,更改 对齐方式");
        ImageSpan imageSpan = new ImageSpan(mContext,R.mipmap.hart);
        ImageSpan imageSpan1 = new ImageSpan(mContext,R.mipmap.buy);
        ImageSpan imageSpan2 = new ImageSpan(mContext,R.mipmap.buy, DynamicDrawableSpan.ALIGN_BASELINE);
        //setSpan 包前不包后覆盖
        //会覆盖调所选位置的文字 故添加空格显示
        //第四个参数控制新插入文本的字体样式  图片随意选择
        spannableString.setSpan(imageSpan,2,3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(imageSpan1,5,6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(imageSpan2,12,13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvOne.setText(spannableString);

        SpannableString spannableString1 = new SpannableString("居中 对齐 YES");
        CustomImageSpan imageSpan3 = new CustomImageSpan(mContext,R.mipmap.hart,2);
        CustomImageSpan imageSpan4 = new CustomImageSpan(mContext,R.mipmap.buy,2);
        spannableString1.setSpan(imageSpan4,5,6,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString1.setSpan(imageSpan3,2,3,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvTwo.setText(spannableString1);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
