package com.example.roomo.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.roomo.Model.Animals;
import com.example.roomo.Utils.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


// CRUD   Create Read Update Delete
// PGPD   POST   GET  PUT    DELETE

    public void addAnimal(Animals animals) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, animals.getName());
        contentValues.put(Util.KEY_SIZE, animals.getSpeices());

        //Insert
        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();


    }

    public Animals getAnimal(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_SIZE}, Util.KEY_ID
                + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Animals animals = new Animals(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2));

        return animals;


    }


    //Get all contacts

    public List<Animals> getAllAnimals() {

        SQLiteDatabase db = getReadableDatabase();

        List<Animals> animalsList = new ArrayList<>();

        //Select All Contacts

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){

            do{

                Animals animals = new Animals();
                animals.setId(Integer.parseInt(cursor.getString(0)));
                animals.setName(cursor.getString(1));
                animals.setSpeices(cursor.getString(2));

                // add animal to list

                animalsList.add(animals);

            }while(cursor.moveToNext());
        }


        return animalsList;
    }

}
