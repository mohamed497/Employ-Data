package com.example.empdata.retrofit;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class EmployViewModel extends ViewModel {

    public MutableLiveData<List<EmployModel>> employMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void getEmploy(){
        compositeDisposable.add(EmployClient.getInstance().getEmpoly()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BiConsumer<List<EmployModel>, Throwable>() {
            @Override
            public void accept(List<EmployModel> employModels, Throwable throwable) throws Exception {
                if (employModels != null){
                    Log.d("surah", " SURAH : " + employModels.size());
                    employMutableLiveData.postValue(employModels);
                }
                else{
                    Log.d("null","null :( :( :(");
                }
            }
        }));
    }



}
