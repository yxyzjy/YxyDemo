package com.example.yxy.yxydemo.MyClassTest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * Created by yxy on 2017/6/19 0019.
 * 自定义ImageSpan实现图片与文字的居中对齐
 */

public class CustomImageSpan extends ImageSpan {

    private int ALIGN_FONTCENTER = 2;

    public CustomImageSpan(Context context, int resourceId) {
        super(context, resourceId);
    }

    public CustomImageSpan(Context mContext, int hart, int i) {
        super(mContext, hart, i);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        //获取画笔的文字绘制时的具体测量数据
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = bottom -drawable.getBounds().bottom;
        if (mVerticalAlignment == ALIGN_BASELINE){
            transY -=fm.descent;
        }else if (mVerticalAlignment == ALIGN_FONTCENTER){
            transY = ((y+fm.descent)+(y+fm.ascent))/2-drawable.getBounds().bottom/2;
        }
        canvas.translate(x,transY);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        Drawable d = getDrawable();
        Rect rect = d.getBounds();
        if (fm != null){
            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
            int fontHeight = fmPaint.bottom -fmPaint.top;
            int drHeight = rect.bottom - rect.top;
            int top = drHeight/2 - fontHeight/4;
            int bottom = drHeight/2+fontHeight/4;

            fm.ascent = -bottom;
            fm.top = -bottom;
            fm.bottom = top;
            fm.descent = top;
        }
        return rect.right;
    }
}
