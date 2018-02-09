package com.andro.prolapso.shapi.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.andro.prolapso.shapi.R;

public class EditDialogBuilder extends AlertDialog.Builder {

    EditDialogBuilder(final ProgrammesActivity activity) {
        super(activity);

        final View dialogView = View.inflate(activity, R.layout.edit_dialog, null);

        final EditText editText = dialogView.findViewById(R.id.edit_dialog);

        setView(dialogView);
        setTitle(R.string.edit_dialog_title);
        setNegativeButton(R.string.cancel, null);
        setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.addProgram(editText.getText().toString());
            }
        });
    }
}
