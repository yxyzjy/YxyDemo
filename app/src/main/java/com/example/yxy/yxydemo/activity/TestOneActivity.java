package com.example.yxy.yxydemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yxy.yxydemo.BaseActivity;
import com.example.yxy.yxydemo.R;
import com.example.yxy.yxydemo.bean.Curse;
import com.example.yxy.yxydemo.bean.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestOneActivity extends BaseActivity {

    String[] names = {"aaa", "Bbb", "ccc"};

    ImageView imageView;
    TextView tv;
    int drawableRes = R.mipmap.ic_launcher;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        super.setContentLayout(savedInstanceState);
        setTopBar(R.layout.activity_test_one, "demo1");

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Base_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, s);
                    }
                });
    }

    @Override
    public void initView() {
        super.initView();
        imageView = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void initData() {
        super.initData();
        List<Curse> curses = new ArrayList<>();
        Curse curse1 = new Curse("English", 90);
        Curse curse2 = new Curse("music", 80);
        curses.add(curse1);
        curses.add(curse2);
        Student student = new Student("a", 22, curses);

        List<Curse> curses1 = new ArrayList<>();
        Curse curse11 = new Curse("aaa", 99);
        Curse curse21 = new Curse("bbb", 100);
        Curse curse331 = new Curse("ccc", 88);
        curses1.add(curse11);
        curses1.add(curse21);
        curses1.add(curse331);
        Student student1 = new Student("a", 22, curses1);
        Student[] students = {student, student1};
    }

    public void test5(Student[] students) {
        Subscriber<Curse> subscriber = new Subscriber<Curse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Curse curse) {
                Log.e(TAG, curse.getCurseName());
            }
        };

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Curse>>() {
                    @Override
                    public Observable<Curse> call(Student student) {
                        return Observable.from(student.getCurse());
                    }
                }).subscribe(subscriber);

    }

    public void test4(Student[] students) {
        Subscriber<Student> sub = new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                List<Curse> cur = student.getCurse();
                for (int i = 0; i < cur.size(); i++) {
                    Curse curse = cur.get(i);
                    Log.e(TAG, "name==" + curse.getCurseName() + "==num==" + curse.getCurseNum());

                }
            }
        };

        Observable.from(students)
                .map(new Func1<Student, Student>() {
                    @Override
                    public Student call(Student student) {
                        return student;
                    }
                })
                .subscribe(sub);


    }


    public void test3() {

        Student[] students = {};

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, s);
                tv.setText(s);
            }
        };
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .subscribe(subscriber);

    }

    public void test2() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "num==" + integer);
                    }
                });
    }

    public void test1() {

        Observable.just(drawableRes)
                .map(new Func1<Integer, Drawable>() {
                    @Override
                    public Drawable call(Integer integer) {
                        return getResources().getDrawable(integer);
                    }
                }).subscribe(new Action1<Drawable>() {
            @Override
            public void call(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        });


      /*  Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable = getTheme().getDrawable(drawableRes);
                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .observeOn(Schedulers.io())//指定 subscribe() 发生在 io线程
                .observeOn(AndroidSchedulers.mainThread())//指定Subscrible的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(TestOneActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }
                });*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
