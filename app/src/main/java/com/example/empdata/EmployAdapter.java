package com.example.empdata;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployAdapter extends RecyclerView.Adapter<EmployAdapter.EmployViewHolder> {


    private List<Employ> employs;
    private Context context;
    private Application application;


    public EmployAdapter(Application application, Context mCtx) {
        this.application = application;
        this.context = mCtx;

    }


    @NonNull
    @Override
    public EmployViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_employ, parent, false);

        return new EmployViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployViewHolder holder, int position) {

        Employ employ = employs.get(position);
        holder.line.setText(employ.line);

    }

    public void setList(List<Employ> employs){
        this.employs = employs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return employs.size();
    }

    public class EmployViewHolder extends RecyclerView.ViewHolder {

        TextView line;
        public EmployViewHolder(@NonNull View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.line);
        }
    }
}
