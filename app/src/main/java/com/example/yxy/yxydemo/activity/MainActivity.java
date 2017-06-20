package com.example.yxy.yxydemo.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.yxy.yxydemo.R;
import com.example.yxy.yxydemo.myview.ItemMainView;

/**
 * Created by yxy on 2017/6/17 0017.
 * 首页  列表页显示所有包含的页面
 */

public class MainActivity extends ListActivity {

    private static class DemoDetails {
        int titleId;
        int descriptionId;
        String title, description;
        Class<? extends Activity> activityClass;

        public DemoDetails(int titleId, int descriptionId, Class<? extends Activity> activityClass) {
            super();
            this.titleId = titleId;
            this.descriptionId = descriptionId;
            this.activityClass = activityClass;
        }

        public DemoDetails(String title, String description, Class<? extends Activity> activityClass) {
            super();
            this.title = title;
            this.description = description;
            this.activityClass = activityClass;
        }
    }

    private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {
        public CustomArrayAdapter(Context context, DemoDetails[] demos) {
            super(context, R.layout.item_main, R.id.title, demos);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ItemMainView itemMainView;
            if (convertView instanceof ItemMainView) {
                itemMainView = (ItemMainView) convertView;
            } else {
                itemMainView = new ItemMainView(getContext());
            }
            DemoDetails demoDetails = getItem(position);
            if (demoDetails.titleId == 0) {
                itemMainView.setTitle(demoDetails.title, demoDetails.activityClass != null);
                itemMainView.setDescription(demoDetails.description);
            } else {
                itemMainView.setTitleId(demoDetails.titleId, demoDetails.activityClass != null);
                itemMainView.setDescriptionId(demoDetails.descriptionId);
            }
            return itemMainView;
        }
    }

    private DemoDetails[] demos = {
            new DemoDetails("Android中文图混排时文图的居中对齐 ", "", TestOneActivity.class),
            new DemoDetails("点击事件，页面跳转", "", TestTwoActivity.class),
            new DemoDetails("Rxjava练习", "", TestThreeActivity.class),
            new DemoDetails("EditText与软键盘", "", TestFourActivity.class),
            new DemoDetails("地图", "", DemoActivity.class)
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter adapter = new CustomArrayAdapter(this.getApplicationContext(), demos);
        setListAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        DemoDetails demoDetails = (DemoDetails) getListAdapter().getItem(position);
        if (demoDetails.activityClass != null) {
            startActivity(new Intent(this.getApplicationContext(), demoDetails.activityClass));
        }
        super.onListItemClick(l, v, position, id);
    }
}
