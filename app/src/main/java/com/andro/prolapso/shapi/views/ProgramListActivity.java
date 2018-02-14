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
    private static final int REQUEST_SHOW_PROGRAM = 156;

    private BddProgramClass mBddProgramClass;

    private ArrayList<Program> mProgramList;
    private ProgramAdapter mProgramAdapter;

    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);

        mBddProgramClass = new BddProgramClass(this);

        final ListView programListView = findViewById(R.id.list_programs);
        mProgramList = mBddProgramClass.getAllPrograms();
        mProgramAdapter = new ProgramAdapter(this, mProgramList);
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

        programListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showChoiceDialog(mProgramList.get(i));
                return true;
            }
        });

        // Add program
        FloatingActionButton btnAdd = findViewById(R.id.btn_add_exercise);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDialogBuilder builder = new EditDialogBuilder(ProgramListActivity.this,
                        R.string.program_list_dialog_create_title, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addProgram(EditDialogBuilder.getText());
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("Acti resu: " + requestCode + " " +resultCode);
        if (requestCode == REQUEST_SHOW_PROGRAM) {
            if (resultCode == RESULT_OK) {
                updateProgram(data.getIntExtra(EXTRA_PROGRAM_ID, -1));
            }
        }

    }

    // Add a program in the database and in the ListView
    private void addProgram(String name) {
        Program newProgram = mBddProgramClass.addProgram(name);
        mProgramList.add(newProgram);
        mProgramAdapter.notifyDataSetChanged();
    }

    // Delete a program from the database and the ListView
    private void deleteProgram(Program program) {
        mBddProgramClass.deleteProgram(program.getId());
        mProgramList.remove(program);
        mProgramAdapter.notifyDataSetChanged();
    }

    // Update interface
    private void updateProgram(int progId) {
        Program program = mBddProgramClass.getProgramsById(Integer.toString(progId));

        for (int i = 0; i < mProgramList.size(); i++) {
            if (mProgramList.get(i).getId() == progId) {
                mProgramList.remove(i);
                mProgramList.add(i, program);
            }
        }

        mProgramAdapter.notifyDataSetChanged();
    }

    // Show dialog which ask whether to delete or not the program
    private void showChoiceDialog(final Program selectedProgram) {

        // Dialog to Change program Name
        View.OnClickListener listenerOne = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EditDialogBuilder(ProgramListActivity.this, R.string.edit_title,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final String newName = EditDialogBuilder.getText();
                                mBddProgramClass.updateProgram(Integer.toString(selectedProgram.getId()), newName);
                                selectedProgram.setName(newName);
                                mProgramAdapter.notifyDataSetChanged();

                                mAlertDialog.dismiss();
                            }
                        }).create().show();
            }
        };

        // Dialog to Delete program
        View.OnClickListener listenerTwo = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProgramListActivity.this);
                builder.setTitle(R.string.delete_title);
                builder.setMessage(R.string.program_list_dialog_delete_message);
                builder.setNegativeButton(R.string.cancel, null);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        deleteProgram(selectedProgram);

                        mAlertDialog.dismiss();
                    }
                });
                builder.create().show();
            }
        };

        // Build choice dialog
        mAlertDialog = new ChoiceDialogBuilder(ProgramListActivity.this, R.string.program_list_dialog_choice_update,
                R.string.program_list_dialog_choice_delete, listenerOne, listenerTwo).create();

        mAlertDialog.show();
    }
}
