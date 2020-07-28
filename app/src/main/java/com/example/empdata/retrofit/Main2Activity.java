package com.example.empdata.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.empdata.R;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    EmployViewModel employViewModel;
    RecyclerView recyclerView;

    EmpAdapter empAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        recyclerView = findViewById(R.id.newRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        employViewModel = ViewModelProviders.of(this).get(EmployViewModel.class);
        employViewModel.getEmploy();
        employViewModel.employMutableLiveData.observe(this, new Observer<List<EmployModel>>() {
            @Override
            public void onChanged(List<EmployModel> employModels) {
                if (employModels != null){
                    Log.d("test"," "+employModels.get(0).salary);
                    empAdapter = new EmpAdapter(getApplication(), Main2Activity.this);
                    empAdapter.setList(employModels);
                    recyclerView.setAdapter(empAdapter);
                }
            }
        });
    }
}
