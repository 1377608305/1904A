package com.example.lenovo.day01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.interfaces.RSAKey;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhuceActivity extends AppCompatActivity {


    private EditText Secname;
    private EditText Secpass;
    private EditText ReSecpass;
    private EditText pho;
    private EditText yanzheng;
    private Button Seczhuce;
    private String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        Secname = findViewById(R.id.Secname);
        Secpass = findViewById(R.id.Secpass);
        ReSecpass = findViewById(R.id.ReSecpass);
        pho = findViewById(R.id.pho);
        yanzheng = findViewById(R.id.yanzheng);
        Seczhuce = findViewById(R.id.Seczhuce);
        Seczhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Secname.getText().toString().trim();
                String pass = Secpass.getText().toString().trim();
                String repass = ReSecpass.getText().toString().trim();
                if (name.length()!=0&&pass.length()!=0&&pass.equals(repass)){
                   
                    intdata();

                }else {
                    Toast.makeText(ZhuceActivity.this, "输入错误，重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void dararegin() {
        final String name = Secname.getText().toString().trim();
        final String pass = Secpass.getText().toString().trim();
        String ph= pho.getText().toString().trim();
        
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://yun918.cn/study/public/index.php/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Secver secver = retrofit.create(Secver.class);
        secver.getregin(name,pass,ph,data).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        String string = responseBody.string();
                        Log.i("TAG", "accept:注册成功"+string);
                        Intent intent = new Intent(ZhuceActivity.this, MainActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("pass",pass);
                            startActivity(intent);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("TAG", "accept:注册失败"+throwable);
                    }
                });
    }

    private void intdata() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://yun918.cn/study/public/index.php/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Secver secver = retrofit.create(Secver.class);
        secver.getyanzheng().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Bean>() {
                    @Override
                    public void accept(Bean bean) throws Exception {
                        data = bean.getData();
                        yanzheng.setText(data);
                        dararegin();
                        Log.i("TAG", "accept:验证成功"+ data);
                        Log.i("TAG", "accept:验证成功"+bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("TAG", "accept:验证失败");
                    }
                });

    }

}
