package com.example.leavemanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ManagerViewHolder> {

    private Context mContext;

    private List<Manager> mManagerList;

    public ManagerAdapter(Context context, List<Manager> managerList) {
        mContext = context;
        mManagerList = managerList;
    }

    @NonNull
    @Override
    public ManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.manager_item, parent, false);
        return new ManagerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerViewHolder holder, int position) {
        Manager manager = mManagerList.get(position);
        holder.textViewName.setText(manager.getName());
        holder.textViewEmail.setText(manager.getEmail());
    }

    @Override
    public int getItemCount() {
        return mManagerList.size();
    }

    public static class ManagerViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewEmail;

        public ManagerViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }
}
