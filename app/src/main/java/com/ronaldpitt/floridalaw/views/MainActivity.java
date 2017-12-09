package com.ronaldpitt.floridalaw.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ronaldpitt.floridalaw.ArrayClass;
import com.ronaldpitt.floridalaw.R;

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
    Button feedbackButton;
    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedbackButton = findViewById(R.id.emailButton);
        shareButton = findViewById(R.id.shareButton);

        arrayClass = new ArrayClass();
        final Intent intent = new Intent(this, ChapterOneActivity.class);

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
                Log.i("MainAct Pos pressed:", String.valueOf(position));


            }
        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp();
            }
        });


       }

    //Variables for send email method
    private String lawEmail = "ronpitt305@gmail.com";
    private String feedbackSubject = "MINI FLORIDA LAW FEEDBACK";
    private String lawText = "";

    //Variables for share method
    private String shareBody = "Share with your friends";
    private String shareSubject = "Share";


    public void sendEmail(){

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{lawEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,feedbackSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, lawText);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Choose an Email Client"));

    }


    public void shareApp(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

}



