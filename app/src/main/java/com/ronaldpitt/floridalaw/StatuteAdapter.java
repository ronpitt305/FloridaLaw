package com.ronaldpitt.floridalaw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ronaldpitt.floridalaw.models.FloridaStatutes;

import java.util.ArrayList;


public class StatuteAdapter extends BaseAdapter {

    Context context;
    ArrayList<FloridaStatutes> floridaStatutesArrayList;

    public StatuteAdapter(Context context, ArrayList<FloridaStatutes> floridaStatutesArrayList) {
        this.context = context;
        this.floridaStatutesArrayList = floridaStatutesArrayList;
    }

    @Override
    public int getCount() {
        return floridaStatutesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.statuterow, viewGroup, false);

        //TextView statute = (TextView) view.findViewById(R.id.sStatute);
        TextView title = (TextView) view.findViewById(R.id.sTitle);
        TextView desc = (TextView) view.findViewById(R.id.sDesc);

        // setting data into textViews
        //statute.setText("Statue");
        title.setText(floridaStatutesArrayList.get(i).getTitle());
        desc.setText(floridaStatutesArrayList.get(i).getDescription());

        return view;
    }
}
