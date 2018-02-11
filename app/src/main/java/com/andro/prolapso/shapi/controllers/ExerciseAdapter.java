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

/**
 * Created by lens on 10/02/2018.
 */

public class ExerciseAdapter extends ArrayAdapter<Exercise> {

    private final Context mContext;

    public ExerciseAdapter(Context context, int textViewResourceId, ArrayList<Exercise> exercises) {
        super(context, textViewResourceId, exercises);
        mContext = context;
    }

    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        final ExerciseAdapter.ExerciseHolder holder;

        final Exercise exercise = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
            holder = new ExerciseAdapter.ExerciseHolder();
            // Set holder attributes
            holder.exerciseName = v.findViewById(android.R.id.text1);
            v.setTag(holder);
        } else {
            holder = (ExerciseAdapter.ExerciseHolder) v.getTag();
        }

        if (exercise == null) return v;

        holder.exerciseName.setText(exercise.getName());

        return v;
    }

    // ViewHolder pattern
    private static class ExerciseHolder {
        TextView exerciseName;
    }

}
