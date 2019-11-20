package com.example.lenovo.day01;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019/11/18.
 */

public interface Secver {
    @POST("study/public/index.php/login")
    Observable<ResponseBody> getlogin(@Query("username") String name,@Query("password")String password);

    @POST("register")
    Observable<ResponseBody> getregin(@Query("username")String username,
                                      @Query("password")String password,
                                      @Query("phone")String phone,
                                      @Query("verify")String verify);

    @GET("verify")
    Observable<Bean> getyanzheng();
}
