package com.andro.prolapso.shapi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddProgramClass;
import com.andro.prolapso.shapi.models.Program;

public class ProgramActivity extends AppCompatActivity {
    private Program program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        final int progId = getIntent().getIntExtra(ProgramListActivity.EXTRA_PROGRAM_ID, -1);
        if (progId == -1) finish();
        else {
            BddProgramClass bddProgramClass = new BddProgramClass(this);
            program = bddProgramClass.getProgramsById(Integer.toString(progId));
            setTitle(program.getName());
        }
    }
}
