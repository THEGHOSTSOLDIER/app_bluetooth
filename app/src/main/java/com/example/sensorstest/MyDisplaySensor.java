package com.example.sensorstest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MyDisplaySensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager; //The sensor manager is a system service that lets you access the device sensors.
    private Sensor mSensor;
    private String mes = "";
    public static final String EXTRA_MESSAGE = "com.example.sensorstest";
    Boolean switchState;

    TextView data0;
    TextView data1;
    TextView data2;
    // initiate a Switch
    Switch simpleSwitch;

    public void sendMessage(View view) {
        // Do something in response to button click
        mSensorManager.unregisterListener(this);
        mes += mSensor.getName().toString() + "\n";
        mes += "Type : " + String.valueOf(mSensor.getType()) + "\n";
        mes += "Vendor : " + mSensor.getVendor().toString() + "\n";
        mes += "Resolution : " + String.valueOf(mSensor.getResolution()) + "\n";
        mes += "Power : " + String.valueOf(mSensor.getPower()) + "\n";
        mes += "Version : " + String.valueOf(mSensor.getVersion()) + "\n";
        mes += "Values :" + "\n";
        mes += data0.getText().toString() + "\n";
        mes += data1.getText().toString() + "\n";
        mes += data2.getText().toString() + "\n";
        switchState = simpleSwitch.isChecked();
        if (switchState) {
            Intent intent = new Intent(this, SendData.class); // from this context to the new activity
            intent.putExtra(EXTRA_MESSAGE, mes);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, BluetoothModule.class); // from this context to the new activity
            intent.putExtra(EXTRA_MESSAGE, mes);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_display_sensor);

        simpleSwitch = (Switch) findViewById(R.id.switchConnection);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); //a string with the type of sensor
        Log.d("sensor",message);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensor = mSensorManager.getDefaultSensor(Integer.parseInt(message));
        Log.d("sensor",mSensor.getName());
        TextView textView = findViewById(R.id.sensorName);
        textView.setText(mSensor.getName());

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    public void onSensorChanged(SensorEvent event) {
        data0 = findViewById(R.id.data0);
        data1 = findViewById(R.id.data1);
        data2 = findViewById(R.id.data2);
        Log.d("sensor", String.valueOf(event.values[0]));
        data0.setText( String.valueOf(event.values[0]));
        data1.setText(String.valueOf(event.values[1]));
        data2.setText(String.valueOf(event.values[2]));
    }



}