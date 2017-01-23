package com.example.administrator.myapplication.rxjava;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.cn.baselib.base.BaseActivity;
import com.cn.baselib.base.BasePresenter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.LogUtils;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxjavaActivity extends BaseActivity {
    @Bind(R.id.btn_click)
    Button btnClick;
    @Bind(R.id.iv_image)
    ImageView imageView;
    @Bind(R.id.listview)
    ListView listview;
    private Observer<String> observer;
    private Subscriber<String> subscriber;
    private Observable<String> observable;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_rxjava;
    }

    @Override
    protected void initView() {
//        testOber();
//        testObservable();
//        testImage();
//        testmap();
    }

    private void testmap() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("123");//发送“123”字符串类型
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Double>() {//将String转换为Double
                    @Override
                    public Double call(String s) {
                        return Double.parseDouble(s);//返回Double
                    }
                })
                .subscribe(new Action1<Double>() {
                    @Override
                    public void call(Double aDouble) {
                        LogUtils.i(aDouble + "");
                    }
                });
    }

    private void testImage() {
        final int drawableRes = R.mipmap.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())//指定subscribe（）发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//指定Subscribe的回调发生在主线程
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxjavaActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }
                });
    }

    private void testObservable() {
//        Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件
        String[] words = {"a", "b", "c"};
        observable = Observable.from(words);
//        observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("a");
//                subscriber.onNext("b");
//                subscriber.onNext("c");
//                subscriber.onCompleted();
//            }
//        });
//        observable.subscribe(observer);
    }

    private void testObserver() {
//        Observer 即观察者，它决定事件触发的时候将有怎样的行为
        observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("tag", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag", "onError:");
            }

            @Override
            public void onNext(String s) {
                Log.i("tag", "onNext: " + s);
            }
        };
//        Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的
        subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i("tag", "onStart:");
            }

            @Override
            public void onCompleted() {
                Log.i("tag", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag", "onError:");
            }


            @Override
            public void onNext(String s) {
                Log.i("tag", "onNext: " + s);
            }
        };

    }

    @OnClick({R.id.btn_click})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_click:
                observable.subscribe(subscriber);//出发事件订阅
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }

}
