package com.mrogeli.sensores1;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView txtSensores;
    private TextView txtGiroscopio;
    private TextView txtRotation;

    private SensorManager sensorManager;
    private List<Sensor> sensores;

    private Sensor sensorG, sensorR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSensores=(TextView) findViewById(R.id.txt_sensores);
        txtGiroscopio=(TextView) findViewById(R.id.txt_Giroscopio);
        txtRotation=(TextView) findViewById(R.id.txt_Rotation);

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensores=sensorManager.getSensorList(Sensor.TYPE_ALL);

        int i=1;
        for (Iterator<Sensor> it = sensores.iterator(); it.hasNext(); i++){
            Sensor sensor = it.next();
            txtSensores.append(String.format("%d:%s,%d, %s\n",i,sensor.getName(), sensor.getType(), sensor.getVendor()));
        }

        sensorG=(Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, sensorG , SensorManager.SENSOR_DELAY_NORMAL);

        sensorR=(Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, sensorR , SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_ROTATION_VECTOR:
                txtRotation.setText(String.format("%f",sensorEvent.values[0]));
                break;
            case Sensor.TYPE_GYROSCOPE:
                txtGiroscopio.setText(String.format("%f",sensorEvent.values[0]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}