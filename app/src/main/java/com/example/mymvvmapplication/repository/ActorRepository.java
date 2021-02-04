package com.example.mymvvmapplication.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import com.example.mymvvmapplication.dao.ActorDao;
import com.example.mymvvmapplication.database.ActorDatabase;
import com.example.mymvvmapplication.model.Actor;

import java.util.List;

public class ActorRepository {

    private ActorDatabase database;
    private LiveData<List<Actor>> getAllActors;
    public ActorRepository(Application application){
        database = ActorDatabase.getInstance(application);
        getAllActors = database.actorDao().getAllActors();
    }
    public void insert(List<Actor> actorList){
        new InsertAsynTask(database).execute(actorList);
    }

    public LiveData<List<Actor>> getGetAllActors(){

        return getAllActors;
    }

    static class InsertAsynTask extends AsyncTask<List<Actor>, Void, Void>{

        private ActorDao actorDao;
        InsertAsynTask(ActorDatabase actorDatabase){
            actorDao = actorDatabase.actorDao();
        }
        @Override
        protected Void doInBackground(List<Actor>... lists) {
            actorDao.insert(lists[0]);
            return null;
        }
    }
}
