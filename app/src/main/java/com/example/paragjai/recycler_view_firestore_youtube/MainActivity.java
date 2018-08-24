package com.example.paragjai.recycler_view_firestore_youtube;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends Activity {

    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<User> userArrayList;
    MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userArrayList = new ArrayList<>();
        setUpRecyclerView();
        setUpFireBase();
        addTestDataToFirebase();
        loadDataFromFirebase();
    }

    private void addTestDataToFirebase()
    {
        Map<String, String> dataMap = new HashMap<String, String>();
        Random random = new Random();
        final Integer for_name = random.nextInt(50);
        final Integer for_status = random.nextInt(50);
        dataMap.put("status", "try status : " + for_status);
        dataMap.put("name", "try name : " + for_name);

        db.collection("users")
                .add(dataMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Log.d("onSuccess called: ", "name: try name " + for_name + " status: try status " + for_status);
                        Toast.makeText(MainActivity.this, "Added Test Data", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void loadDataFromFirebase()
    {
        if(userArrayList.size()>0)
            userArrayList.clear();

        /*get ALL documents in the "users" collection*/
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {

                        if(task.isSuccessful())
                        {
                            Log.d("onComplete called", "task is successful: " );
                            Integer i = 0;
                            QuerySnapshot querySnapshot = task.getResult();

                            if(querySnapshot.isEmpty())
                            {
                                Log.d("onComplete called", "no documents in collection");
                            }
                            else {
                                String nameFromDoc;
                                String statusFromDoc;
                                Log.d("onComplete called", querySnapshot.size() + " documents present in collection");
                                for (DocumentSnapshot documentSnapshot : querySnapshot)
                                {
                                    i = i + 1;
                                    Log.d("onComplete : " + i, "i :" + i);
                                    Log.d("doc id: " + documentSnapshot.getId(), documentSnapshot.getData().toString()  );
                                    statusFromDoc = documentSnapshot.getString("status");
                                    nameFromDoc = documentSnapshot.getString("name");
                                    Log.d("status from doc: ", statusFromDoc);
                                    Log.d("name from doc: ", nameFromDoc);
                                    /*User ctor takes name first and then status*/
                                    User user = new User(nameFromDoc, statusFromDoc);
                                    userArrayList.add(user);
                                }

                                Log.d("size of userArrayList: ", "is: " + userArrayList.size());
                                myRecyclerViewAdapter = new MyRecyclerViewAdapter(MainActivity.this, userArrayList);
                                mRecyclerView.setAdapter(myRecyclerViewAdapter);
                            }
                        }
                        else
                        {
                            Exception exception = task.getException();
                            Log.d("loadDataFromFirebase: ", "exception occured: " + exception);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Problem occured", Toast.LENGTH_SHORT).show();
                        Log.w("onFailure called : ", e.getMessage());
                    }
                });
    }

    private void setUpRecyclerView()
    {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpFireBase()
    {
        db = FirebaseFirestore.getInstance();
    }


}
