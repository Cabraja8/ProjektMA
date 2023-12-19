package com.example.projektma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTask#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTask extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private EditText editTaskTitle;
    private EditText editTaskDescription;
    private CheckBox checkBoxCompleted;
    private Button buttonPickDueDate;
    private Button buttonCreateTask;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddTask() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodaysTasks.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTask newInstance(String param1, String param2) {
        AddTask fragment = new AddTask();
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        editTaskTitle = view.findViewById(R.id.editTaskTitle);
        editTaskDescription = view.findViewById(R.id.editTaskDescription);
        checkBoxCompleted = view.findViewById(R.id.checkBoxCompleted);
        buttonPickDueDate = view.findViewById(R.id.buttonPickDueDate);
        buttonCreateTask = view.findViewById(R.id.buttonCreateTask);

        // Set up onClick listener for buttonPickDueDate
        buttonPickDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle buttonPickDueDate click (e.g., show date picker)
                showDatePickerDialog();
            }
        });
        buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle buttonCreateTask click (e.g., save task)
                createTask();
            }
        });



        return view;
    }

    private void showDatePickerDialog() {
        // Implement your logic to show date picker
    }

    private void createTask() {
        // Implement your logic to create a task
    }
}
