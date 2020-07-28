package com.example.empdata.retrofit;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployClient {

    private static final String BASE_URL = "http://10.0.2.2/company/";
    public  EmployInterface employInterface;
    private static EmployClient INSTANCE;
    Retrofit retrofit;


    public EmployClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        employInterface = retrofit.create(EmployInterface.class);

    }

    public static EmployClient getInstance(){
        if(INSTANCE == null)
            INSTANCE = new EmployClient();
        return INSTANCE;
    }

    public Single<List<EmployModel>> getEmpoly(){
        return employInterface.getData().map(new Function<EmpResponse, List<EmployModel>>() {
            @Override
            public List<EmployModel> apply(EmpResponse empResponse) throws Exception {
                if(empResponse != null){
//                    return listeningResponse.DataObject.surahs;
                    Log.d("zxc","SIZE : : "+empResponse.emp.size());
                }
                else{
                    Log.d("zxc","NULLLL : ");
                }
                return empResponse.emp;
            }
        });
    }

}
