package com.example.empdata.retrofit;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.empdata.Employ;
import com.example.empdata.EmployAdapter;
import com.example.empdata.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.EmployViewHolder> {


    private List<EmployModel> employs;
    private Context context;
    private Application application;

    public EmpAdapter(Application application, Context mCtx) {
        this.application = application;
        this.context = mCtx;

    }


    @NonNull
    @Override
    public EmployViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_emp, parent, false);

        return new EmployViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployViewHolder holder, int position) {

        EmployModel employ = employs.get(position);
        holder.name.setText(employ.name);
        holder.age.setText(employ.age);
        holder.phone.setText(employ.phone);
        holder.id.setText(employ.id);


    }

    @Override
    public int getItemCount() {
        return employs.size();
    }
    public void setList(List<EmployModel> employs){
        this.employs = employs;
        notifyDataSetChanged();
    }

    public class EmployViewHolder extends RecyclerView.ViewHolder {

        TextView name,age,phone,id;

        public EmployViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            phone = itemView.findViewById(R.id.phone);
            id = itemView.findViewById(R.id.salary);
        }
    }
}
