package com.example.mymvvmapplication.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mymvvmapplication.dao.ActorDao;
import com.example.mymvvmapplication.model.Actor;

//table
@Database(entities = {Actor.class} ,version = 1)
public abstract class ActorDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "ActorDatabase";

    //to call functions present in actor dao
    public abstract ActorDao actorDao();

    //instance create
    //volatile = when we close the app, the instance get removed from memory
    private static volatile ActorDatabase INSTANCE;

    //can get insertion through other classes
    //create database
    public static ActorDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (ActorDatabase.class){
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context, ActorDatabase.class, DATABASE_NAME).addCallback(callback).build();
                }
            }
        }
        return INSTANCE;
    }

     static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };

    //background
    static class PopulateAsynTask extends AsyncTask<Void, Void, Void>{

        private ActorDao dao;
        PopulateAsynTask(ActorDatabase actorDatabase){
            dao = actorDatabase.actorDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.delete();
            return null;
        }
    }


}
