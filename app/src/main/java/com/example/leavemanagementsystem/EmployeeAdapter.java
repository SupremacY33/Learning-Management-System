package com.example.leavemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>{

    Context context;
    ArrayList<Employee> employeeList;
    ImageView imageView;

    public EmployeeAdapter(Context context, ArrayList<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_details,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.MyViewHolder holder, int position) {

        Employee employee = employeeList.get(position);

        holder.showemployeename.setText(employee.getFullname());
        holder.showemployeeemail.setText(employee.getEmail());
        holder.showemployeepassword.setText(employee.getPassword());
        holder.showemployee_conform_password.setText(employee.getConform_password());

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView showemployeename,showemployeeemail,showemployeepassword,showemployee_conform_password;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            showemployeename = itemView.findViewById(R.id.showemployeename);
            showemployeeemail = itemView.findViewById(R.id.showemployeeemail);
            showemployeepassword = itemView.findViewById(R.id.showemployeepassword);
            showemployee_conform_password = itemView.findViewById(R.id.showemployee_conformpassword);

        }

    }
}
