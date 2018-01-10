package com.example.gabriel.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;


/**
 * 创建Observable
 */
public class CreateObservablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_observables);
        initCreate();
        initJust();
        initFrom();
        initDefer();
    }

    /**
     * Create
     */
    private void initCreate(){

        findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("hello world,Rxjava");
                    }
                }).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("btnCreate onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("btnCreate onError "+e.getMessage(),e);
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.d("btnCreate onNext "+s);
                    }
                });
            }
        });
    }

    /**
     * Just
     */
    private void initJust(){

        findViewById(R.id.btnJust).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.just("hello world, just").subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("btnJust onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("btnJust onError "+e.getMessage(),e);
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.d("btnJust onNext "+s);
                    }
                });
            }
        });
    }

    /**
     * from
     */
    private void initFrom(){
        findViewById(R.id.btnFrom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.from(new Integer[]{1,2,3,4,5,6,7}).subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("btnFrom onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("btnFrom onError "+e.getMessage(),e);
                    }

                    @Override
                    public void onNext(Integer s) {
                        Logger.d("btnFrom onNext "+s);
                    }
                });
            }
        });
    }

    String valueStr;
    /**
     * Defer 直到订阅才会创建Observable
     */
    private void initDefer(){

        findViewById(R.id.btnDefer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Observable observable = Observable.defer(new Func0<Observable<String>>() {
//                    @Override
//                    public Observable<String> call() {
//                        return Observable.just(valueStr);
//                    }
//                });

                Observable observable = Observable.just(valueStr);
                valueStr = "hello, world, defer, test jenkins";
                observable.subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("btnDefer onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("btnDefer onError "+e.getMessage(),e);
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.d("btnDefer onNext "+s);
                    }
                });
            }
        });
    }
}
