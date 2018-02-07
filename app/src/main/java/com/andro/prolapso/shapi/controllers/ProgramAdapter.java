package com.andro.prolapso.shapi.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ProgramAdapter extends ArrayAdapter<Program> {

    private final Context mContext;

    public ProgramAdapter(Context context, int textViewResourceId, ArrayList<Program> programs) {
        super(context, textViewResourceId, programs);
        mContext = context;
    }

    @Override @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        ProgramHolder holder;

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
            holder = new ProgramHolder();
            // Set holder attributes

            v.setTag(holder);
        } else {
            holder = (ProgramHolder) v.getTag();
        }

        return v;
    }

    // ViewHolder pattern
    private static class ProgramHolder {
        // TODO: add attributes
    }

}
