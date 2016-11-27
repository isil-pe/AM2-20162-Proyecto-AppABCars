package com.isil.abcars.storage.request;

import com.isil.abcars.storage.entity.ListPostsResponse;
import com.isil.abcars.storage.entity.LoginRaw;
import com.isil.abcars.storage.entity.LoginResponse;
import com.isil.abcars.storage.entity.MarcaResponse;
import com.isil.abcars.storage.entity.PerfilRaw;
import com.isil.abcars.storage.entity.PerfilResponse;
import com.isil.abcars.storage.entity.RegisterRaw;
import com.isil.abcars.storage.entity.RegisterResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public class ApiClient {

    private static final String API_BASE_URL="http://api.backendless.com";

    private static ServicesApiInterface servicesApiInterface;
    private static OkHttpClient.Builder httpClient;


    public static ServicesApiInterface getMyApiClient() {

        if (servicesApiInterface == null) {

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(interceptor());

            Retrofit retrofit = builder.client(httpClient.build()).build();
            servicesApiInterface = retrofit.create(ServicesApiInterface.class);
        }
        return servicesApiInterface;
    }

    public interface ServicesApiInterface {

        @Headers({
                "Content-Type: application/json",
                "application-id: 80BC9F6A-858E-0D7B-FFC8-521538D16100",
                "secret-key: A0DBF8ED-09B6-F356-FF2F-9246519D3400",
                "application-type: REST"
        })
        @POST("/v1/users/login")
        Call<LoginResponse> login(@Body LoginRaw raw);


        @Headers({
                "Content-Type: application/json",
                "application-id: 80BC9F6A-858E-0D7B-FFC8-521538D16100",
                "secret-key: A0DBF8ED-09B6-F356-FF2F-9246519D3400",
                "application-type: REST"
        })
        @GET("/v1/data/marca")
        Call<MarcaResponse> marcas();


        @Headers({
                "Content-Type: application/json",
                "application-id: 80BC9F6A-858E-0D7B-FFC8-521538D16100",
                "secret-key: A0DBF8ED-09B6-F356-FF2F-9246519D3400",
                "application-type: REST"
        })
        @POST("/v1/data/users")
        Call<RegisterResponse> addRegister(@Body RegisterRaw raw);

        @Headers({
                "Content-Type: application/json",
                "application-id: 80BC9F6A-858E-0D7B-FFC8-521538D16100",
                "secret-key: A0DBF8ED-09B6-F356-FF2F-9246519D3400",
                "application-type: REST"
        })
        @POST("/v1/users/login")
        Call<PerfilResponse> getPerfil(@Body PerfilRaw raw);


        @Headers({
                "Content-Type: application/json",
                "application-id: 80BC9F6A-858E-0D7B-FFC8-521538D16100",
                "secret-key: A0DBF8ED-09B6-F356-FF2F-9246519D3400",
                "application-type: REST"
        })
        @PUT("v1/data/users")
        Call<PerfilResponse> setPerfil(@Body PerfilRaw raw);


        @Headers({
                "Content-Type: application/json",
                "application-id: 80BC9F6A-858E-0D7B-FFC8-521538D16100",
                "secret-key: A0DBF8ED-09B6-F356-FF2F-9246519D3400",
                "application-type: REST"
        })
        @GET("/v1/data/post")
        Call<ListPostsResponse> listarPosts();


    }

    private  static  HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
