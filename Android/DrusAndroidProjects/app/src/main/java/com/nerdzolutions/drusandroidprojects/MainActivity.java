package com.nerdzolutions.drusandroidprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_projects);

        Project[] projects = {
                new Project("Getting Started", "Our very first project, default 'Hello World!' app", R.drawable.getting_started),
                new Project("Dru Quote", "Making a simple change to the layout", R.drawable.quote),
                new Project("BMI Calculator", "Working BMI calculator teaching me Variables, Methods, and Conditional Logic", R.drawable.tape),
                new Project("Inches Converter", "Basic converter from inches to meters (first app created on my own)", R.drawable.calculator),
                new Project("The Hungry Developer", "Menu app for fictional restaurant teaching me Activities, Classes & Objects, Array, Intents, and ListViews", R.drawable.hungry_developer)
        };

        ProjectsAdapter adapter = new ProjectsAdapter(projects);

        recyclerView.setAdapter(adapter);
    }
}