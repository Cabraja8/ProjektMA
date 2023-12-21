package com.example.projektma;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import android.app.DatePickerDialog;

import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView textSelectedDate;
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

        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        editTaskTitle = view.findViewById(R.id.editTaskTitle);
        editTaskDescription = view.findViewById(R.id.editTaskDescription);
        checkBoxCompleted = view.findViewById(R.id.checkBoxCompleted);
        buttonPickDueDate = view.findViewById(R.id.buttonPickDueDate);
        buttonCreateTask = view.findViewById(R.id.buttonCreateTask);
        textSelectedDate = view.findViewById(R.id.textSelectedDate);
        buttonPickDueDate.setVisibility(View.GONE);

        buttonPickDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog();
            }
        });

        checkBoxCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    buttonPickDueDate.setVisibility(View.VISIBLE);
                    textSelectedDate.setText(" ");
                } else {

                    buttonPickDueDate.setVisibility(View.GONE);
                    textSelectedDate.setText(" ");
                    textSelectedDate.setVisibility(View.GONE);
                }
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
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                textSelectedDate.setText("Selected Date: " + selectedDate);
                textSelectedDate.setVisibility(View.VISIBLE);
                Toast.makeText(requireContext(), "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        }, year, month, dayOfMonth);


        datePickerDialog.show();
    }

    private void createTask() {
        // Get task details
        String title = editTaskTitle.getText().toString();
        String description = editTaskDescription.getText().toString();
        String date = textSelectedDate.getText().toString();

        // Check if all required fields are filled
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
            // Call the addTask method in TaskList to update the list
            TaskList taskListFragment = (TaskList) requireActivity()
                    .getSupportFragmentManager().findFragmentById(R.id.listViewTasks);

            if (taskListFragment != null) {
                taskListFragment.addTask(title);
                Toast.makeText(requireContext(), "Task Created", Toast.LENGTH_SHORT).show();
            }

            // Display a message or navigate back as needed
        } else {
            // Display an error message if required fields are not filled
            Toast.makeText(requireContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
        }
    }
}
