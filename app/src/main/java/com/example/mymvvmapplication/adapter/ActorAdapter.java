package com.example.mymvvmapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymvvmapplication.R;
import com.example.mymvvmapplication.model.Actor;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.AdapterViewHolder> {
    private Context context;
    private List<Actor> actors;


    public ActorAdapter(Context context, List<Actor> actors) {
        this.context = context;
        this.actors = actors;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_actor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Actor actor = actors.get(position);
        holder.id.setText("Id "+actor.getId());
        holder.name.setText("Name "+actor.getName());
        holder.age.setText("Age "+actor.getAge());
        Glide.with(context).load(actor.getImage()).into(holder.imageView);
    }

    public void getAllActors(List<Actor> actorList){
        this.actors = actorList;
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView id, name, age;
        ImageView imageView;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
