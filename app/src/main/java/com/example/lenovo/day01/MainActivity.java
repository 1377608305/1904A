package com.example.lenovo.day01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private Button login;
    private Button zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        zhuce = findViewById(R.id.zhuce);
        String na = getIntent().getStringExtra("name");
        String pa = getIntent().getStringExtra("pass");
        name.setText(na);
        pass.setText(pa);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZhuceActivity.class);
                startActivity(intent);
            }
        });
       login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initData() {
        String trim = name.getText().toString().trim();
        String trim1 = pass.getText().toString().trim();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yun918.cn/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Secver secver = retrofit.create(Secver.class);
        Observable<ResponseBody> observable = secver.getlogin(trim, trim1);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        Log.i("TAG", "accept:"+string);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("TAG", "accept:"+throwable);
                    }
                });
    }
}