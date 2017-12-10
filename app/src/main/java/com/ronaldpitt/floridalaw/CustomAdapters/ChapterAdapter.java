package com.ronaldpitt.floridalaw.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ronaldpitt.floridalaw.R;
import com.ronaldpitt.floridalaw.models.FloridaStatutes;

import java.util.ArrayList;


public class ChapterAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> floridaStatutesArrayList;

    public ChapterAdapter(Context context, ArrayList<String> floridaStatutesArrayList) {
        this.context = context;
        this.floridaStatutesArrayList = floridaStatutesArrayList;
    }

    @Override
    public int getCount() {
        return floridaStatutesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.chapterlayout, viewGroup, false);
        }

        TextView chapter = (TextView) view.findViewById(R.id.sChapter);

        chapter.setText(floridaStatutesArrayList.get(i));

        return view;
    }
}
