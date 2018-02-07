package com.andro.prolapso.shapi.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.ProgramAdapter;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ProgrammesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView programListView = findViewById(R.id.list_programs);
        final ArrayList<Program> programList = new ArrayList<>();  // TODO:  =  get Programs From DB
        // TODO: custom layout for each item
        final ProgramAdapter programAdapter = new ProgramAdapter(this, android.R.layout.simple_list_item_1, programList);
        programListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Program selectedProgram = programList.get(i);
            }
        });

        FloatingActionButton btnAdd = findViewById(R.id.btn_add_program);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Call CreateProgramActivity here
            }
        });
    }

}
