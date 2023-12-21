package com.example.projektma;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private ListView listViewTasks;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> taskTitles;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllTasks.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskList newInstance(String param1, String param2) {
        TaskList fragment = new TaskList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        // Retrieve the ListView from the layout
        listViewTasks = view.findViewById(R.id.listViewTasks);

        // Sample data for the list (replace this with your actual data)
        taskTitles = new ArrayList<>();
        taskTitles.add("Task 1");
        taskTitles.add("Task 2");
        taskTitles.add("Task 3");

        // Create an ArrayAdapter to populate the ListView
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, taskTitles);

        // Set the adapter for the ListView
        listViewTasks.setAdapter(adapter);

        // Set up item click listener to show task details when a task is clicked
        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTaskTitle = taskTitles.get(position);

                // Call a method to show the details of the selected task
                showTaskDetails(selectedTaskTitle);
            }
        });

        return view;
    }

    // Method to add a new task and update the list
    public void addTask(String taskTitle) {
        // Add the new task to the list
        taskTitles.add(taskTitle);

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();
    }

    private void showTaskDetails(String taskTitle) {
        // Replace this with logic to display the task details fragment
        // You may want to create a new fragment similar to TaskDetailsFragment
        // and replace the current fragment with the new one.
        // Pass the task details (title, description, date) to the new fragment.
    }
}
