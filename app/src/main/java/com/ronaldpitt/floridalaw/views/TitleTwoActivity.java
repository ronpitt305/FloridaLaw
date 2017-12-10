package com.ronaldpitt.floridalaw.views;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.ronaldpitt.floridalaw.ManagerClass;
import com.ronaldpitt.floridalaw.R;
import com.ronaldpitt.floridalaw.CustomAdapters.StatuteAdapter;
import com.ronaldpitt.floridalaw.models.FloridaStatutes;

import java.util.ArrayList;

public class TitleTwoActivity extends AppCompatActivity {

    ListView lawList;
    int requestedChapter = 0;
    int requestTitle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        lawList = (ListView) findViewById(R.id.lawList);
        Intent intent = getIntent();

        ArrayList<String> descriptionArrayList = intent.getStringArrayListExtra("descriptionArray");

        //Got titles array
        final ArrayList<Integer> titlesArray = intent.getIntegerArrayListExtra("titleArray");

        ArrayList<FloridaStatutes> floridaArray = intent.getParcelableArrayListExtra("floridaArray");
        StatuteAdapter statuteAdapter = new StatuteAdapter(this, floridaArray);

        requestedChapter = intent.getIntExtra("requestedTitle", 0);

        // ItemList
        lawList.setAdapter(statuteAdapter);
        lawList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                requestTitle = titlesArray.get(position);

                //Async Task
                ChapterLoader loader = new ChapterLoader();
                loader.execute();
            }
        });

    }

    private class ChapterLoader extends AsyncTask<Void, Void, Void> {

        Intent intent = new Intent(TitleTwoActivity.this, StatuteThreeActivity.class);

        @Override
        protected Void doInBackground(Void... voids) {

            //Housekeeping
            ManagerClass managerClass = new ManagerClass();
            CognitoCredentialsProvider credentialsProvider = managerClass.getCredentials(TitleTwoActivity.this);
            AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
            DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);


            FloridaStatutes floridaStatutes = mapper.load(FloridaStatutes.class, requestedChapter, requestTitle);

            intent.putExtra("statute", floridaStatutes.getStatute());
            startActivity(intent);

            return null;

        }
    }
}
