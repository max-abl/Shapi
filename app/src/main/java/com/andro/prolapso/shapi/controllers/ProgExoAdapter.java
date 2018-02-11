package com.andro.prolapso.shapi.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.models.ProgExo;

import java.util.ArrayList;

public class ProgExoAdapter extends ArrayAdapter<ProgExo> {
    private final Context mContext;

    public ProgExoAdapter(Context context, ArrayList<ProgExo> progExos) {
        super(context, R.layout.prog_exo, progExos);
        mContext = context;
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        final ProgExoHolder holder;

        final ProgExo progExo = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.prog_exo , parent, false);
            holder = new ProgExoHolder();

            // Set holder attributes
            holder.name = v.findViewById(R.id.text_prog_exo_name);
            holder.repetition = v.findViewById(R.id.text_prog_exo_repetition);
            holder.serie = v.findViewById(R.id.text_prog_exo_serie);
            holder.time = v.findViewById(R.id.text_prog_exo_time);
            holder.weight = v.findViewById(R.id.text_prog_exo_weight);

            v.setTag(holder);
        } else {
            holder = (ProgExoHolder) v.getTag();
        }

        if (progExo == null) return v;

        // Set TextViews for the item
        holder.name.setText(progExo.getExo().getName());
        holder.repetition.setText(mContext.getString(R.string.program_repetition, progExo.getRepetition()));
        holder.serie.setText(mContext.getString(R.string.program_serie, progExo.getSerie()));
        if (!progExo.getTime().equals("0")) {
            holder.time.setText(mContext.getString(R.string.program_time, progExo.getTime()));
            holder.time.setVisibility(View.VISIBLE);
        } else holder.time.setVisibility(View.GONE);
        if (!progExo.getWeight().equals("0")) {
            holder.weight.setText(mContext.getString(R.string.program_weight, progExo.getWeight()));
            holder.weight.setVisibility(View.VISIBLE);
        } else holder.weight.setVisibility(View.GONE);

        return v;
    }

    // ViewHolder pattern
    private static class ProgExoHolder {
        TextView name, repetition, serie, time, weight;
    }
}
