package com.andro.prolapso.shapi.views;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.andro.prolapso.shapi.R;

// Dialog displaying 2 choices
class ChoiceDialogBuilder extends AlertDialog.Builder {

    ChoiceDialogBuilder(Context context, @StringRes int text1, @StringRes int text2, View.OnClickListener listener1, View.OnClickListener listener2) {
        super(context);

        final View dialogView = View.inflate(context, R.layout.choice_dialog, null);

        final TextView choice1 = dialogView.findViewById(R.id.text_choice1),
            choice2 = dialogView.findViewById(R.id.text_choice2);

        setView(dialogView);

        setTitle(R.string.programmes_dialog_choice_title);
        choice1.setText(text1);
        choice2.setText(text2);

        choice1.setOnClickListener(listener1);
        choice2.setOnClickListener(listener2);
    }

}
