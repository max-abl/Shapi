package com.andro.prolapso.shapi.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.ProgramAdapter;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ProgrammesActivity extends AppCompatActivity {
    private static final int REQUEST_NEW_PROGRAM = 124;

    private ArrayList<Program> mProgramList;
    private ProgramAdapter mProgramAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmes);


        final ListView programListView = findViewById(R.id.list_programs);
        mProgramList = new ArrayList<>();  // TODO:  =  get Programs From DB
        // TODO: custom layout for each item
        mProgramAdapter = new ProgramAdapter(this, android.R.layout.simple_list_item_1, mProgramList);
        programListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Program selectedProgram = mProgramList.get(i);
            }
        });

        FloatingActionButton btnAdd = findViewById(R.id.btn_add_program);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ProgrammesActivity.this, CreateProgramActivity.class), REQUEST_NEW_PROGRAM);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_NEW_PROGRAM) {
            if (resultCode == RESULT_OK) {
                // TODO: Update mProgramList

                mProgramAdapter.notifyDataSetChanged();
            }
        }
    }

}
