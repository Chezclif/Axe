package llc.taurusgroup.exampleapp.data.rest;

import java.util.concurrent.TimeUnit;
import llc.taurusgroup.exampleapp.data.rest.api.RestServiceSimple;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestManager {
    public static String SERVER_PATH = "https://";

    public RestServiceSimple getRestServiceAutorization() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.readTimeout(1, TimeUnit.MINUTES);
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        Retrofit.Builder adapterBuilder = new Retrofit.Builder();
        Retrofit retrofit = adapterBuilder
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_PATH)
                .client(httpClient.build())
                .build();
        RestServiceSimple service = retrofit.create(RestServiceSimple.class);
        return service;
    }
}
