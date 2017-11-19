package com.ronaldpitt.floridalaw;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;



public class ManagerClass {

    public CognitoCachingCredentialsProvider getCredentials(Context context){
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context, "us-east-1:e6abc520-3d8d-4c4d-9a49-4e258042592a", Regions.US_EAST_1
        );
        return credentialsProvider;
    }
}
