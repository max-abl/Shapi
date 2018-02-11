package com.andro.prolapso.shapi.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.models.Exercise;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ProgramAdapter extends ArrayAdapter<Program> {

    private final Context mContext;

    public ProgramAdapter(Context context, ArrayList<Program> programs) {
        super(context, android.R.layout.simple_list_item_2, programs);
        mContext = context;
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        final ProgramHolder holder;

        final Program program = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
            holder = new ProgramHolder();
            // Set holder attributes
            holder.programName = v.findViewById(android.R.id.text1);
            holder.exerciseCount = v.findViewById(android.R.id.text2);
            v.setTag(holder);
        } else {
            holder = (ProgramHolder) v.getTag();
        }

        if (program == null) return v;

        holder.programName.setText(program.getName());
        holder.exerciseCount.setText(mContext.getString(R.string.text_exercise_count, program.getExercises().size()));

        return v;
    }

    // ViewHolder pattern
    private static class ProgramHolder {
        TextView programName, exerciseCount;
    }

}
