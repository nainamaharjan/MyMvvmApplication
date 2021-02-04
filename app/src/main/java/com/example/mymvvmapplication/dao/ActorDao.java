package com.example.mymvvmapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mymvvmapplication.model.Actor;

import java.util.List;

@Dao
public interface ActorDao {

    //insert data and replace duplicate data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Actor> actorList);

    @Query("SELECT * FROM actor")
    LiveData<List<Actor>> getAllActors();

    @Query("DELETE FROM actor")
    void delete();




}
