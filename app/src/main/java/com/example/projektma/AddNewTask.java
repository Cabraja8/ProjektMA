package com.example.projektma;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.projektma.Model.ToDoModel;
import com.example.projektma.Utils.DatabaseHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private EditText newTaskText;

    private Button newTaskSaveButton;
    boolean isUpdate = false;
    private DatabaseHandler db;

    public static AddNewTask newInstance(){
        return new AddNewTask();
    }

    @Override
    public void onCreate (Bundle savedInstance){
        super.onCreate(savedInstance);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);

    }



    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_task, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated  (@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        newTaskText = getView().findViewById(R.id.newTaskText);

        newTaskSaveButton = getView().findViewById(R.id.newTaskButton);

        db = new DatabaseHandler(getActivity());
        db.openDatabase();



        final Bundle bundle = getArguments();

        if(bundle!= null){
            isUpdate = true;
            String task = bundle.getString("task");
            newTaskText.setText(task);
            if(task.length() > 0){
                newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.greenColor));
            }
            newTaskText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().equals("")){
                        newTaskSaveButton.setEnabled(false);
                        newTaskSaveButton.setTextColor(Color.GRAY);
                    }else{
                        newTaskSaveButton.setEnabled(true);
                        newTaskSaveButton.setTextColor(Color.GREEN);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = newTaskText.getText().toString();
                    if(isUpdate){
                        db.updateTask(bundle.getInt("id"),text);
                    }else{
                        ToDoModel task = new ToDoModel();
                        task.setTask(text);
                        task.setStatus(0);
                    }
                    dismiss();

                }
            });


        }

    }
    @Override
    public void onDismiss( DialogInterface dialog) {
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
