package com.example.mymvvmapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mymvvmapplication.adapter.ActorAdapter;
import com.example.mymvvmapplication.model.Actor;
import com.example.mymvvmapplication.network.API;
import com.example.mymvvmapplication.repository.ActorRepository;
import com.example.mymvvmapplication.viewModel.ActorViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ActorViewModel actorViewModel;
    private static final String URL_DATA = "http://www.codingwithjks.tech/data.php/";
    private ActorAdapter actorAdapter;
    private ActorRepository actorRepository;
    private List<Actor> actorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        setRecyclerView();
        actorRepository = new ActorRepository(getApplication());
        actorList = new ArrayList<>();
        actorAdapter = new ActorAdapter(this, actorList);
        actorViewModel = new ViewModelProvider(this).get(ActorViewModel.class);
        networkReq();
        actorViewModel.getGetAllActors().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actorList) {
                actorAdapter.getAllActors(actorList);
                recyclerView.setAdapter(actorAdapter);
                Log.d("hello", "onChanged: "+actorList);
//                Toast.makeText(MainActivity.this, "working", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void networkReq() {
        Log.d("hello", "onResponse: heklloo");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_DATA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        api.getAllActor().enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                if(response.isSuccessful()){
                    Log.d("hello", "onResponse: heloo");
                    actorRepository.insert(response.body());

                }else {
                    Log.d("hello", "onResponse:error heloo");

                }
            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {
                Log.d("hello", "onFailure: "+t.getMessage().toString());
                Log.d("hello", "onFailure:fail ");
            }
        });
//        Call<List<Actor>> call = api.getAllActor();
////        call.enqueue(new Callback<List<Actor>>() {
////            @Override
////            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
////                Log.d("hello", "onResponse: heklloo");
////
////                if(response.isSuccessful()){
////                    Log.d("hello", "onResponse: "+response.body().get(0));
////                    actorRepository.insert(response.body());
////                }
////            }
////
////            @Override
////            public void onFailure(Call<List<Actor>> call, Throwable t) {
////                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
////            }
////        });
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}