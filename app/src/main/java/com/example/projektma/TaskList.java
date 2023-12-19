package com.example.projektma;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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

        // Set up your Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        // Set up your ListView
        ListView listView = view.findViewById(R.id.listViewTasks);

        // Example data for the ListView
        String[] exampleItems = {"Task 1", "Task 2", "Task 3"};

        // Use an ArrayAdapter to populate the ListView with example data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, exampleItems);
        listView.setAdapter(adapter);

        return view;
    }
}