package com.andro.prolapso.shapi.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.andro.prolapso.shapi.models.ProgExo;

import java.util.ArrayList;

public class ProgExoAdapter extends ArrayAdapter<ProgExo> {
    private final Context mContext;

    public ProgExoAdapter(Context context, ArrayList<ProgExo> progExos) {
        // TODO: change resource
        super(context, android.R.layout.simple_list_item_2, progExos);
        mContext = context;
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        final ProgExoHolder holder;

        final ProgExo progExo = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
            holder = new ProgExoHolder();
            // Set holder attributes
            // TODO

            v.setTag(holder);
        } else {
            holder = (ProgExoHolder) v.getTag();
        }

        if (progExo == null) return v;


        return v;
    }

    // ViewHolder pattern
    private static class ProgExoHolder {

    }
}
