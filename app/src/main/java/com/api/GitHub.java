package com.api;

import com.bean.Contributor;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);

    @POST("post")
    @FormUrlEncoded
    Call<ResponseBody> startPost(@Field("username") String username, @Field("pwd") String pwd);

    @POST("post")
    Call<ResponseBody> getMessage(@Body RequestBody info);

    //http://oss-cn-hangzhou.aliyuncs.com
    @Multipart
    @POST("/")
    Call<ResponseBody> postUpLoadFile(@Part() MultipartBody.Part requestBody);
}
