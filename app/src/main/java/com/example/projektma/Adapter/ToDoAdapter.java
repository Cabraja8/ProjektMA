package com.example.projektma.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projektma.AddNewTask;
import com.example.projektma.MainActivity;
import com.example.projektma.Model.ToDoModel;
import com.example.projektma.R;
import com.example.projektma.Utils.DatabaseHandler;

import org.w3c.dom.Text;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    public List<ToDoModel> toDoList;
    private MainActivity activity;
    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db,MainActivity activity){
        this.db = db;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        db.openDatabase();

        ToDoModel item = toDoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.date.setText(item.getDate());
        holder.desc.setText(item.getDesc());

        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(),1);
                }else{
                    db.updateStatus(item.getId(),0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int itemCount = toDoList.size();

        Log.d("TaskLIST TAG", "Task List Size: " + itemCount);

        if (itemCount == 0) {
            activity.isEmpty = true;
            activity.enableNoTasksLayout();  // Enable the LinearLayout
        } else {
            activity.isEmpty = false;
            activity.disableNoTasksLayout(); // Disable the LinearLayout
        }

        return itemCount;
    }


    private boolean toBoolean(int n){
        return n!=0;
    }

    public void setTasks(List<ToDoModel> toDoList){
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return activity;
    }

    public void deleteItem(int position){
        ToDoModel item = toDoList.get(position);
        db.deleteTask(item.getId());
        toDoList.remove(position);
        notifyItemRemoved(position);
    }


    public void editItem(int position){
        ToDoModel item= toDoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id",item.getId());
        bundle.putString("task",item.getTask());
        bundle.putString("description",item.getDesc());
        bundle.putString("date",item.getDate());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(),AddNewTask.TAG);
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        CheckBox task;
        TextView date;
        TextView desc;

        ViewHolder(View view){
            super(view);
            task= view.findViewById(R.id.todoCheckBox);
            date = view.findViewById(R.id.dateTextView);
            desc = view.findViewById(R.id.descriptionTextView);
        }
    }

}
