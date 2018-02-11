package com.andro.prolapso.shapi.views;

import android.app.AlertDialog;
import android.support.annotation.StringRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.andro.prolapso.shapi.R;

class EditDialogBuilder extends AlertDialog.Builder {
    private static String text;

    EditDialogBuilder(final ProgramListActivity activity, @StringRes int title, AlertDialog.OnClickListener listener) {
        super(activity);

        text = "";

        final View dialogView = View.inflate(activity, R.layout.edit_dialog, null);

        final EditText editText = dialogView.findViewById(R.id.edit_dialog);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setView(dialogView);
        setTitle(title);
        setNegativeButton(R.string.cancel, null);
        setPositiveButton(R.string.ok, listener);
    }

    static String getText() {
        return text;
    }

    @Override
    public AlertDialog create() {
        AlertDialog dialog = super.create();

        // Show Keyboard
        if (dialog.getWindow() != null)
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        return dialog;
    }
}
