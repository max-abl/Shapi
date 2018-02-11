package com.andro.prolapso.shapi.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andro.prolapso.shapi.models.Exercise;
import com.andro.prolapso.shapi.views.ExerciseListActivity;

import java.util.ArrayList;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
    private Context mContext;

    public ExerciseAdapter(ExerciseListActivity activity, ArrayList<Exercise> exercises) {
        super(activity, android.R.layout.simple_list_item_2, exercises);
        mContext = activity;
    }


    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        final ExerciseHolder holder;

        final Exercise exercise = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
            holder = new ExerciseHolder();
            // Set holder attributes
            holder.name = v.findViewById(android.R.id.text1);
            holder.type = v.findViewById(android.R.id.text2);
            v.setTag(holder);
        } else {
            holder = (ExerciseHolder) v.getTag();
        }

        if (exercise == null) return v;

        holder.name.setText(exercise.getName());
        holder.type.setText(exercise.getType());

        return v;
    }

    // ViewHolder pattern
    private static class ExerciseHolder {
        TextView name, type;
    }
}
