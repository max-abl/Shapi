package com.andro.prolapso.shapi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddProgexoClass;
import com.andro.prolapso.shapi.controllers.BddProgramClass;
import com.andro.prolapso.shapi.controllers.ProgExoAdapter;
import com.andro.prolapso.shapi.models.ProgExo;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ProgramActivity extends AppCompatActivity {
    private BddProgexoClass mBbddProgexoClass;
    private ArrayList<ProgExo> mProgExercises;
    private int idProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        final int progId = getIntent().getIntExtra(ProgramListActivity.EXTRA_PROGRAM_ID, -1);
        if (progId == -1) finish();
        else {
            final BddProgramClass bddProgramClass = new BddProgramClass(this);
            mBbddProgexoClass = new BddProgexoClass(this, bddProgramClass);

            final Program program = bddProgramClass.getProgramsById(Integer.toString(progId));

            setTitle(program.getName());

            idProgram = program.getId();

            final ListView progExoListView = findViewById(R.id.list_exercises);
            mProgExercises = mBbddProgexoClass.getAllExoProgram(Integer.toString(idProgram));
            final ProgExoAdapter progExoAdapter = new ProgExoAdapter(this, mProgExercises);
            progExoListView.setAdapter(progExoAdapter);

            progExoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // TODO
                    return false;
                }
            });
        }
    }
}
