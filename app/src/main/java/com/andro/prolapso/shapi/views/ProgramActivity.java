package com.andro.prolapso.shapi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddProgexoClass;
import com.andro.prolapso.shapi.controllers.BddProgramClass;
import com.andro.prolapso.shapi.models.Program;

public class ProgramActivity extends AppCompatActivity {
    private BddProgexoClass mBbddProgexoClass;
    private int idProgram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        final int progId = getIntent().getIntExtra(ProgramListActivity.EXTRA_PROGRAM_ID, -1);
        if (progId == -1) finish();
        else {
            BddProgramClass bddProgramClass = new BddProgramClass(this);
            mBbddProgexoClass = new BddProgexoClass(this, bddProgramClass);
            Program program = bddProgramClass.getProgramsById(Integer.toString(progId));
            setTitle(program.getName());

            idProgram = program.getId();

            final ListView progExoListView = findViewById(R.id.list_exercises);

        }
    }
}
