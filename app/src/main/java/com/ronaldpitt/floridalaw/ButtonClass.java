package com.ronaldpitt.floridalaw;


import android.content.Intent;

public class ButtonClass {

    //Variables for send email method
    private String lawEmail = "ronpitt305@gmail.com";
    private String feedbackSubject = "MINI FLORIDA LAW FEEDBACK";
    private String lawText = "Enter text here";


    public void sendEmail(){

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, lawEmail);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,feedbackSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, lawText);


    }
}
