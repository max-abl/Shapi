package com.andro.prolapso.shapi.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddProgramClass;
import com.andro.prolapso.shapi.controllers.ProgramAdapter;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ProgramListActivity extends AppCompatActivity {
    static final String EXTRA_PROGRAM_ID = "extra_program_id";
    static final int REQUEST_SHOW_PROGRAM = 156;

    private BddProgramClass mBddProgramClass;

    private ArrayList<Program> mProgramList;
    private ProgramAdapter mProgramAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmes);

        mBddProgramClass = new BddProgramClass(this);

        final ListView programListView = findViewById(R.id.list_programs);
        mProgramList = mBddProgramClass.getAllPrograms();
        mProgramAdapter = new ProgramAdapter(this, android.R.layout.simple_list_item_2, mProgramList);
        programListView.setAdapter(mProgramAdapter);
        programListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Program selectedProgram = mProgramList.get(i);
                Intent intent = new Intent(ProgramListActivity.this, ProgramActivity.class);
                intent.putExtra(EXTRA_PROGRAM_ID, selectedProgram.getId());
                startActivityForResult(intent, REQUEST_SHOW_PROGRAM);
            }
        });

        // Show dialog which ask whether to delete or not the program
        programListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProgramListActivity.this);
                builder.setTitle(R.string.programmes_dialog_delete_title);
                builder.setMessage(R.string.programmes_dialog_delege_message);
                builder.setNegativeButton(R.string.cancel, null);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        deleteProgram(mProgramList.get(i));
                    }
                });
                builder.create().show();
                return false;
            }
        });

        FloatingActionButton btnAdd = findViewById(R.id.btn_add_program);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EditDialogBuilder(ProgramListActivity.this).create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SHOW_PROGRAM) {
            System.out.println("Result Code: "+resultCode);
            if (resultCode == RESULT_OK) {
                mProgramList = mBddProgramClass.getAllPrograms();
                mProgramAdapter.notifyDataSetChanged();
            }
        }

    }

    void addProgram(String name) {
        Program newProgram = mBddProgramClass.addProgram(name);

        System.out.println(newProgram);
        mProgramList.add(newProgram);
        System.out.println(newProgram.getName());
        mProgramAdapter.notifyDataSetChanged();
    }

    private void deleteProgram(Program program) {
        mBddProgramClass.deleteProgram(program.getId());
        mProgramList.remove(program);
        mProgramAdapter.notifyDataSetChanged();
    }

}
