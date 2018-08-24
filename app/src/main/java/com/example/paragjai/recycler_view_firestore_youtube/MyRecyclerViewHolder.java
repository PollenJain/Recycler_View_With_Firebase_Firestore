package com.example.paragjai.recycler_view_firestore_youtube;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mUserName;
    public TextView mUserStatus;
    public Button mDeleteRow;

    public MyRecyclerViewHolder(View itemView)
    {
        super(itemView);
        mUserName = itemView.findViewById(R.id.tvUserName);
        mUserStatus = itemView.findViewById(R.id.tvUserStatus);
        mDeleteRow = itemView.findViewById(R.id.bDeleteRow);
    }
}


