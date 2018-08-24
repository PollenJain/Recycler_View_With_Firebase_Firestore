package com.example.paragjai.recycler_view_firestore_youtube;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    MainActivity mainActivity_;
    ArrayList<User> userArrayList_;


    public MyRecyclerViewAdapter(MainActivity mainActivity, ArrayList<User> userArrayList)
    {
        this.mainActivity_ = mainActivity;
        this.userArrayList_ = userArrayList;
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
       // LayoutInflater layoutInflater = LayoutInflater.from(mainActivity_.getBaseContext());
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.single_row, viewGroup, false);
        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder myRecyclerViewHolder, int i) {

            myRecyclerViewHolder.mUserName.setText(userArrayList_.get(i).getUserName());
            myRecyclerViewHolder.mUserStatus.setText(userArrayList_.get(i).getUserStatus());

    }

    @Override
    public int getItemCount() {
        return userArrayList_.size();
    }
}
