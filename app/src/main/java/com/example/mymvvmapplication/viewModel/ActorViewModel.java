package com.example.mymvvmapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymvvmapplication.model.Actor;
import com.example.mymvvmapplication.repository.ActorRepository;

import java.util.List;


//actor repository call
public class ActorViewModel extends AndroidViewModel {

    private ActorRepository actorRepository;
    private LiveData<List<Actor>> getAllActors;

    public ActorViewModel(@NonNull Application application) {
        super(application);
        actorRepository = new ActorRepository(application);
        getAllActors = actorRepository.getGetAllActors();
    }

    public void insert(List<Actor> actorList){
        actorRepository.insert(actorList);
    }

    public LiveData<List<Actor>> getGetAllActors(){
        return getAllActors;
    }
}
