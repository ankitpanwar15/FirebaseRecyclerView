package com.proact.ankit.FirebaseRecyclerView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VideoListRetrive extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> wholePdfVideosKeys= new ArrayList<>();
    private ArrayList<String> perticularVideosKeys = new ArrayList<>();
    private ArrayList<String> wholeVideoListLinks = new ArrayList<>();
    private ArrayList<String> perticularVideosLinks = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    Bundle b;
    int videoStart;
    int videoEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_list_retrive);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("11th_class");
        DatabaseReference  myref = databaseReference.child("videos");
        b = getIntent().getExtras();
        videoStart = b.getInt("videoStart");
        videoEnd = b.getInt("videoEnd");








        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,perticularVideosKeys);

        listView = (ListView) findViewById(R.id.videolistview);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(VideoListRetrive.this,Videos.class);
                String url = perticularVideosLinks.get(i);
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });


        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    wholePdfVideosKeys.add(childDataSnapshot.getKey());
                    wholeVideoListLinks.add((String) childDataSnapshot.getValue());
                }

                for(int i=videoStart;i<videoEnd;i++) {
                    perticularVideosKeys.add(wholePdfVideosKeys.get(i));
                    perticularVideosLinks.add(wholeVideoListLinks.get(i));
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
