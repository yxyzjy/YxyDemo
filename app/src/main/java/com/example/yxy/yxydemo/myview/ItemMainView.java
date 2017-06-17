package com.example.yxy.yxydemo.myview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yxy.yxydemo.R;

/**
 * Created by yxy on 2017/6/17 0017.
 */

public class ItemMainView extends FrameLayout {
    public ItemMainView(@NonNull Context context) {
        super(context);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.item_main, this);
    }

    public synchronized void setTitle(String title, boolean isSub) {
        if (isSub) {
            ((TextView) (findViewById(R.id.title))).setText("   " + title);
        } else {
            ((TextView) (findViewById(R.id.title))).setText(title);
        }
    }

    public synchronized void setDescription(String description) {
        ((TextView) (findViewById(R.id.description))).setText(description);
    }

    public synchronized void setTitleId(int titleId, boolean isSub) {
        String title = this.getResources().getString(titleId);
        if (isSub) {
            ((TextView) (findViewById(R.id.title))).setText("   " + title);
        } else {
            ((TextView) (findViewById(R.id.title))).setText(title);
        }
    }

    public synchronized void setDescriptionId(int descriptionId) {
        ((TextView) (findViewById(R.id.description))).setText(descriptionId);
    }


}
