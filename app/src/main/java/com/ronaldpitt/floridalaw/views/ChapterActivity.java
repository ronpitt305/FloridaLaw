package com.ronaldpitt.floridalaw.views;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.ronaldpitt.floridalaw.ArrayClass;
import com.ronaldpitt.floridalaw.ManagerClass;
import com.ronaldpitt.floridalaw.R;
import com.ronaldpitt.floridalaw.models.FloridaStatutes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChapterActivity extends AppCompatActivity {

    ListView listView;
    ArrayClass arrayClass;
    int receivedInterger = 0;
    ArrayList<Integer> chapterArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_ichap);

        Intent intent = getIntent();
        final String[] receivedArray = intent.getStringArrayExtra("chapters");
        final int numChapter = intent.getIntExtra("chapterNumber", 0);
        Log.i("Current numChapter", String.valueOf(numChapter));

        arrayClass = new ArrayClass();
        chapterArray = new ArrayList<>();


        listView = (ListView) findViewById(R.id.lawList);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(receivedArray));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                receivedInterger = position + 1;

                TitleGetter chapterGetter = new TitleGetter();
                chapterGetter.execute();
            }
        });


    }

    private class TitleGetter extends AsyncTask<Void, Void, Void>{

        Intent intent = new Intent(ChapterActivity.this, TitleActivity.class);

        @Override
        protected Void doInBackground(Void... voids) {
            ManagerClass managerClass = new ManagerClass();
            CognitoCredentialsProvider credentialsProvider = managerClass.getCredentials(ChapterActivity.this);
            AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);

            //Hash conditions
            FloridaStatutes floridaStatutes = new FloridaStatutes();
            floridaStatutes.setChapter(receivedInterger);

            DynamoDBQueryExpression<FloridaStatutes> queryExpression = new DynamoDBQueryExpression<>();
            queryExpression.withHashKeyValues(floridaStatutes);
                    //.withRangeKeyCondition("chapter", rangeCondition);

            PaginatedQueryList<FloridaStatutes> chapterList = mapper.query(FloridaStatutes.class, queryExpression);

                ArrayList<String> chapterArray = new ArrayList<>();
                ArrayList<Integer> titlesArrayList = new ArrayList<>();


                for (int i = 0; i < chapterList.size(); i++){
                    chapterArray.add(chapterList.get(i).getDescription());
                    titlesArrayList.add(chapterList.get(i).getTitle());
                    Log.i("Title array contains", titlesArrayList.get(i).toString());

                }



            intent.putExtra("chapterArray", chapterArray);
            intent.putExtra("titles", titlesArrayList);
            intent.putExtra("receivedInterger", receivedInterger);
            startActivity(intent);


            return null;
        }
    }

}
