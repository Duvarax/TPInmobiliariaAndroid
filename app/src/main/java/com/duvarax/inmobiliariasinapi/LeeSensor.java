package com.duvarax.inmobiliariasinapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.net.Uri;

import androidx.core.app.ActivityCompat;

public class LeeSensor implements SensorEventListener {
    private Context context;

    public LeeSensor(Context context) {
        this.context = context;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double umbral = 20.0;
        if(sensorEvent.values[0] > umbral || sensorEvent.values[1] > umbral || sensorEvent.values[2] > umbral){
            String phoneNumber = "0800";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+ phoneNumber));

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
