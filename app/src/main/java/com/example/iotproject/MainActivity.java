package com.example.iotproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MainActivity extends AppCompatActivity {
    TextView humidity,temperature,level,moistureView,lastWaterDate;
    DatabaseReference mDatabase;

    Button btn, highButton,averageButton,lowButton;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        humidity=(TextView)findViewById(R.id.humid);
        temperature=(TextView)findViewById(R.id.temp);
        lastWaterDate=(TextView)findViewById(R.id.lastDate);
        btn = (Button) findViewById(R.id.btn);
        highButton = (Button) findViewById(R.id.button5);
        averageButton = (Button) findViewById(R.id.button6);
        lowButton = (Button) findViewById(R.id.button7);



                mDatabase = FirebaseDatabase.getInstance().getReference().child("asgm");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String humid=dataSnapshot.child("Humidity").getValue().toString();
                        String temp=dataSnapshot.child("Temp").getValue().toString();
                        String moisture=dataSnapshot.child("moisture level").getValue().toString();
                        String lastDate=dataSnapshot.child("last watering").getValue().toString();
                        humidity.setText(humid);
                        temperature.setText(temp);
                        lastWaterDate.setText(lastDate);

                        highButton.setClickable(false);
                        averageButton.setClickable(false);
                        lowButton.setClickable(false);

                        if(moisture.equals("High")){
                            highButton.setEnabled(true);
                            averageButton.setEnabled(false);
                            lowButton.setEnabled(false);
                            btn.setEnabled(false);
                       }else if (moisture.equals("Average")){
                            highButton.setEnabled(false);
                            averageButton.setEnabled(true);
                            lowButton.setEnabled(false);
                            btn.setEnabled(true);
                        }else if(moisture.equals("Low")){
                            highButton.setEnabled(false);
                            averageButton.setEnabled(false);
                            lowButton.setEnabled(true);
                            btn.setEnabled(true);
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError error) {

                        Log.w(TAG, "Failed to read value.", error.toException());

                    }
                });



                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mDatabase.child("led").setValue("1");
                        openDialog();

                    }
                });

    }

public void openDialog(){
        msgDialog message = new msgDialog();
        message.show(getSupportFragmentManager(), "message dialog");
}



}