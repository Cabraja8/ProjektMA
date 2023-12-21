package com.example.projektma;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.projektma.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReplaceFragment(new TaskList());
        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            int itemId = item.getItemId();

            if (itemId == R.id.TaskList) {
                ReplaceFragment(new TaskList());
            } else if (itemId == R.id.AddTask) {
                ReplaceFragment(new AddTask());
            } else if (itemId == R.id.Completed) {
                ReplaceFragment(new Completed_tasks());
            }

            return true;
        });
    }
    private void ReplaceFragment (Fragment fragLayout){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragLayout);
        fragmentTransaction.commit();
    }

}