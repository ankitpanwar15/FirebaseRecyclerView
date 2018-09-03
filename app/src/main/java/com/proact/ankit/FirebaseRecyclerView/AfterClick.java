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

public class AfterClick extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> wholePdfListKeys= new ArrayList<>();
    private ArrayList<String> perticularPdfsKeys = new ArrayList<>();
    private ArrayList<String> wholePdfListLinks = new ArrayList<>();
    private ArrayList<String> perticularPdfsLinks = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    Bundle b;
    int pdfStart;
    int pdfSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_click);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("11th_class");
        DatabaseReference  myref = databaseReference.child("pdf");
        b = getIntent().getExtras();
        pdfStart = b.getInt("pdfStart");
        pdfSize = b.getInt("pdfSize");








        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,perticularPdfsKeys);

        listView = (ListView) findViewById(R.id.pdflistview);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AfterClick.this,PdfWebView.class);
                String url = perticularPdfsLinks.get(i);
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });


        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    wholePdfListKeys.add(childDataSnapshot.getKey());
                    wholePdfListLinks.add((String) childDataSnapshot.getValue());
                }

                for(int i=pdfStart;i<pdfSize;i++) {
                    perticularPdfsKeys.add(wholePdfListKeys.get(i));
                    perticularPdfsLinks.add(wholePdfListLinks.get(i));
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
