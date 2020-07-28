package com.example.empdata.retrofit;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface EmployInterface {

    @GET("employ.php")
    public Single<EmpResponse> getData();

}
