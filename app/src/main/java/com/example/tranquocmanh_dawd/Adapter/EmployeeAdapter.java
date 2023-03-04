package com.example.tranquocmanh_dawd.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tranquocmanh_dawd.Database.Employee;
import com.example.tranquocmanh_dawd.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Employee> listEmployee;

    public EmployeeAdapter(Activity activity, List<Employee> listEmployee) {
        this.activity = activity;
        this.listEmployee = listEmployee;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.item_employee, parent, false);
        EmployeeHolder holder = new EmployeeHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int position) {
        EmployeeHolder vh = (EmployeeHolder) holder;
        Employee employee = listEmployee.get(position);
        vh.tvID.setText(employee.id + "");
        vh.tvName.setText(employee.getName());
        vh.tvSalary.setText(employee.getSalary());
        vh.tvDesignation.setText(employee.getDesignation());
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public class EmployeeHolder extends RecyclerView.ViewHolder {
        private TextView tvID, tvName, tvSalary, tvDesignation;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvID);
            tvName = itemView.findViewById(R.id.tvName);
            tvSalary = itemView.findViewById(R.id.tvSalary);
            tvDesignation = itemView.findViewById(R.id.tvDesignation);
        }
    }
}
