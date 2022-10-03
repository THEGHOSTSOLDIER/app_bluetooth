package com.example.sensorstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import java.util.List;



public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager; //The sensor manager is a system service that lets you access the device sensors.
    private RecyclerView mRecyclerView; // the class RecyclerView is Android defined
    private SensorListAdapter mSensorListAdapter; // the class SensorListAdapter  must be written by you
    public static final String EXTRA_MESSAGE = "com.example.sensorstest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList  =      mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor currentSensor : sensorList ) {
            Log.d("sensor",currentSensor.getName());
            Log.d("sensor",String.valueOf(currentSensor.getType()));
        }
        // Create an adapter and supply the data to be displayed.
        mSensorListAdapter = new SensorListAdapter(this, sensorList); //constructor of your SensorListAdapter class
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.IDrecyclerView);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mSensorListAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void myFunction(View view){ // when we click on the "List of sensors"
        Log.d("sensor","myfunction");
        Intent intent = new Intent(this,MyDisplaySensor.class); // from this context to the new activity
        //String message = "Hello";
        String message = "1";//accelerometer
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}