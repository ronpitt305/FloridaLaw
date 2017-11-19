package com.ronaldpitt.floridalaw.models;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "florida_statutes")
public class FloridaStatutes {

    private int chapter;
    private int title;
    private String description;
    private String statute;

    public FloridaStatutes() {
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
}
