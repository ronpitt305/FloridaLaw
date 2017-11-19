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
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.ronaldpitt.floridalaw.ManagerClass;
import com.ronaldpitt.floridalaw.R;
import com.ronaldpitt.floridalaw.models.FloridaStatutes;

import java.util.ArrayList;

public class TitleActivity extends AppCompatActivity {

    ListView lawList;
    int pressedStatute = 0;
    int receivedInterger = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        //Setting up from Chapter Activity
        lawList = (ListView) findViewById(R.id.lawList);
        Intent intent = getIntent();
        ArrayList<Integer> arrayList = intent.getIntegerArrayListExtra("chapterArray");
        final ArrayList<Integer> titlesList = intent.getIntegerArrayListExtra("titles");
        receivedInterger = intent.getIntExtra("receivedInterger", 0);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        // ItemList
        lawList.setAdapter(arrayAdapter);
        lawList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pressedStatute = titlesList.get(position);

                //Init Async task
                Loader loader = new Loader();
                loader.execute();
            }
        });

    }

    private class Loader extends AsyncTask<Void, Void, Void> {

        Intent intent = new Intent(TitleActivity.this, StatuteActivity.class);

        @Override
        protected Void doInBackground(Void... voids) {

            //Housekeeping
            ManagerClass managerClass = new ManagerClass();
            CognitoCredentialsProvider credentialsProvider = managerClass.getCredentials(TitleActivity.this);
            AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);

            //Actual load code
            FloridaStatutes floridaStatutes = mapper.load(FloridaStatutes.class, receivedInterger, pressedStatute);
            Log.i("Uploaded Law", floridaStatutes.getStatute());

                intent.putExtra("statute", floridaStatutes.getStatute());
                startActivity(intent);


            return null;

        }
    }
}
