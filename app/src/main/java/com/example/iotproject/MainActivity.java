package com.example.iotproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView a,b,c,d;
    DatabaseReference mDatabase;
    Button btn;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=(TextView)findViewById(R.id.humid);
        b=(TextView)findViewById(R.id.temp);
        c=(TextView)findViewById(R.id.text);
        d=(TextView)findViewById(R.id.moisture);

                mDatabase = FirebaseDatabase.getInstance().getReference().child("asgm");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String humid=dataSnapshot.child("Humidity").getValue().toString();
                        String temp=dataSnapshot.child("Temp").getValue().toString();
                        String text=dataSnapshot.child("Text").getValue().toString();
                        String moisture=dataSnapshot.child("Moisture").getValue().toString();
                        a.setText(humid);
                        b.setText(temp);
                        c.setText(text);
                        d.setText(moisture);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });










        // Read from the database

    }




}