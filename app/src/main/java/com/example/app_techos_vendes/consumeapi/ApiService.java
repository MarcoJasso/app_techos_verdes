package com.example.app_techos_vendes.consumeapi;

import com.example.app_techos_vendes.apimodelsresponse.ApiResponseModel;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {

    @Headers("x-access-tokens: P@ssw0rD12")
    @GET("plants/getall")
    Call<ApiResponseModel> getRespuesta();

    @Headers("x-access-tokens: P@ssw0rD12")
    @POST
    Call<ApiResponseModel> addNewUser(@Url String dataUser);

    @Headers("x-access-tokens: P@ssw0rD12")
    @GET
    Call<ApiResponseModel> singIn(@Url String dataUser);

    @Headers("x-access-tokens: P@ssw0rD12")
    @GET("store/getall")
    Call<ApiResponseModel> getAllStore();

    @Headers("x-access-tokens: P@ssw0rD12")
    @POST("plants/insertFav")
    Call<ApiResponseModel> addFav(@Url String dataUser);

    @Headers("x-access-tokens: P@ssw0rD12")
    @DELETE("plants/deletefav")
    Call<ApiResponseModel> deleteFav(@Url String dataUser);

    @Headers("x-access-tokens: P@ssw0rD12")
    @GET("plants/favorite")
    Call<ApiResponseModel> getAllFav();



}
