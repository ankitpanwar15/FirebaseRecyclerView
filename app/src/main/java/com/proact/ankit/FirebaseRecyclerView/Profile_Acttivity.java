package com.proact.ankit.FirebaseRecyclerView;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Profile_Acttivity extends AppCompatActivity {
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabaseOld;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Blog, Profile_Acttivity.BlogViewHolder> mPeopleRVAdapter;
    DatabaseReference myPdf;
    static ArrayList arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__acttivity);

        setTitle("News");

        //"News" here will reflect what you have called your database in Firebase.
        mDatabaseOld = FirebaseDatabase.getInstance().getReference().child("11th_class");
        mDatabase = mDatabaseOld.child("Chapters");
        mDatabase.keepSynced(true);

        mPeopleRV = (RecyclerView) findViewById(R.id.myRecycleView);

        RecyclerView.LayoutManager recyce = new GridLayoutManager(Profile_Acttivity.this,2);

        final DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child("11th_class");
        Query personsQuery = mDatabase;

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(recyce);
        mPeopleRV.setItemAnimator( new DefaultItemAnimator());

        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<Blog>().setQuery(personsQuery, Blog.class).build();

        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Blog, Profile_Acttivity.BlogViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(Profile_Acttivity.BlogViewHolder holder, final int position, final Blog model) {
                holder.setName(model.getName());
                holder.setImage(getBaseContext(), model.getImage());


//                holder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        final int videoStart = model.getVideoStart();
//                        final int videoEnd = model.getVideoEnd();
//                        Intent intent = new Intent(getApplicationContext(),VideoListRetrive.class);
//                        intent.putExtra("videoStart",videoStart);
//                        intent.putExtra("videoEnd",videoEnd);
//                        startActivity(intent);
//                    }
//                });


                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int pdfStart = model.getPdfStart();
                        final int pdfSize = model.getPdfEnd();
                        Intent intent = new Intent(getApplicationContext(), AfterClick.class);
                        intent.putExtra("pdfStart",pdfStart);
                        intent.putExtra("pdfSize",pdfSize);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public Profile_Acttivity.BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.blog_row, parent, false);

                return new Profile_Acttivity.BlogViewHolder(view);
            }
        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPeopleRVAdapter.stopListening();


    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name){
           TextView Name = (TextView) mView.findViewById(R.id.post_title);
           Name.setText(name);
        }

        public void setImage(final Context ctx, final String image){
            final ImageView post_image = (ImageView) mView.findViewById(R.id.whyImage);
//            Picasso.with(ctx).load(image).into(post_image);

            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).into(post_image);
                }
            });
        }

    }

}