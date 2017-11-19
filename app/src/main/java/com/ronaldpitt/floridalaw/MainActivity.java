package com.ronaldpitt.floridalaw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.ronaldpitt.floridalaw.views.ChapterActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> titles;
    String[] titleArray = {"Title I", "Title II", "Title III", "Title IV", "Title V", "Title VI", "Title VII", "Title VIII",
            "Title IX", "Title X", "Title XI", "Title XII", "Title XIII", "Title XIV", "Title XV", "Title XVI", "Title XVII",
            "Title XVIII", "Title XIX", "Title XX", "Title XXI", "Title XXII", "Title XXIII", "Title XXIV", "Title XXV",
            "Title XXVI", "Title XXVII", "Title XXVIII", "Title XXIX", "Title XXX", "Title XXXI", "Title XXXII", "Title XXXIII",
            "Title XXXIV", "Title XXXV", "Title XXXVI", "Title XXXVII", "Title XXXVIII", "Title XXXIX", "Title XL", "Title XLI",
            "Title XLII", "Title XLIII", "Title XLIV", "Title XLV", "Title XLVI", "Title XLII", "Title XLIII"};

    ArrayClass arrayClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayClass = new ArrayClass();
        final Intent intent = new Intent(this, ChapterActivity.class);

        titles = new ArrayList<>(Arrays.asList(titleArray));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);

        listView = (ListView) findViewById(R.id.lawList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("List item clicked", "Title " + Integer.toString(position));
                intent.putExtra("chapters", arrayClass.getArray(position));
                intent.putExtra("chapterNumber", position+1);
                startActivity(intent);


            }
        });


       }

}

