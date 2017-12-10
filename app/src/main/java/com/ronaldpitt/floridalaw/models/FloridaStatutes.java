package com.ronaldpitt.floridalaw.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "florida_statutes")
public class FloridaStatutes implements Parcelable{

    private int chapter;
    private int title;
    private String description;
    private String statute;

    public FloridaStatutes() {
    }


    public FloridaStatutes(int chapter, int title, String description){
        this.chapter = chapter;
        this.title = title;
        this.description = description;
    }



    public FloridaStatutes(int chapter, int title, String description, String statute) {
        this.chapter = chapter;
        this.title = title;
        this.description = description;
        this.statute = statute;
    }



    @DynamoDBHashKey(attributeName = "chapter")
    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    @DynamoDBRangeKey(attributeName = "title")
    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "statute")
    public String getStatute() {
        return statute;
    }

    public void setStatute(String statute) {
        this.statute = statute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(chapter);
        parcel.writeInt(title);
        parcel.writeString(description);

    }

    public static final Parcelable.Creator<FloridaStatutes> CREATOR = new Creator<FloridaStatutes>() {
        @Override
        public FloridaStatutes createFromParcel(Parcel parcel) {
            FloridaStatutes mFloridaStatutes = new FloridaStatutes();
            mFloridaStatutes.chapter = parcel.readInt();
            mFloridaStatutes.title = parcel.readInt();
            mFloridaStatutes.description = parcel.readString();
            return mFloridaStatutes;
        }

        @Override
        public FloridaStatutes[] newArray(int i) {
            return new FloridaStatutes[0];
        }
    };
}
