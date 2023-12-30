package com.example.projektma;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projektma.Adapter.ToDoAdapter;
import com.example.projektma.Model.ToDoModel;
import com.example.projektma.Utils.DatabaseHandler;
import com.example.projektma.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements  DialogCloseListener {

    private RecyclerView taskRecyclerView;
    private ToDoAdapter taskAdapter;

    private FloatingActionButton fab;

    private LinearLayout noTaskLayout;

    private List<ToDoModel> taskList;

    private DatabaseHandler db;

    public boolean isEmpty ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noTaskLayout = findViewById(R.id.NoTask);



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();



     

        taskRecyclerView = findViewById(R.id.tasksRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new ToDoAdapter(db,this);
        taskRecyclerView.setAdapter(taskAdapter);

        fab = findViewById(R.id.fab);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper (new RecyclerItemTouchHelper((taskAdapter)));

        itemTouchHelper.attachToRecyclerView(taskRecyclerView);

        taskList = db.getAllTasks();

        Collections.reverse(taskList);
        taskAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);

            }
        });

    }
    public void enableNoTasksLayout() {
        if (noTaskLayout != null) {
            noTaskLayout.setVisibility(View.VISIBLE);
        }
    }

    public void disableNoTasksLayout() {
        if (noTaskLayout != null) {
            noTaskLayout.setVisibility(View.GONE);
        }
    }

    private void checkNoTasksVisibility() {
        if (noTaskLayout != null) {
            if (taskAdapter.getItemCount() == 0) {
                enableNoTasksLayout();
            } else {
                disableNoTasksLayout();
            }
        }
    }
    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setTasks(taskList);
        taskAdapter.notifyDataSetChanged();

    }

}