package com.example.libb.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "SensorData";

    private SensorManager sensorManager;
    private TextView accX,accY,accZ;
    private TextView magX,magY,magZ;
    private TextView gyroX,gyroY,gyroZ;
    private Button btnStart,btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        Sensor acceSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        Sensor gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        Sensor magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        initView();
    }

    private void initView() {
        accX = (TextView) findViewById(R.id.accX);
        accY = (TextView) findViewById(R.id.accY);
        accZ = (TextView) findViewById(R.id.accZ);

        magX = (TextView) findViewById(R.id.magX);
        magY = (TextView) findViewById(R.id.magY);
        magZ = (TextView) findViewById(R.id.magZ);

        gyroX = (TextView) findViewById(R.id.gyroX);
        gyroY = (TextView) findViewById(R.id.gyroY);
        gyroZ = (TextView) findViewById(R.id.gyroZ);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
    }

    public void onClick(View V){

    }
    @Override
    protected void onResume() {
        super.onResume();
        // 为方向传感器注册监听器
        sensorManager.registerListener(listenerAcce,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(listenerMag,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(listenerGyro,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_UI);
    }

    private SensorEventListener listenerAcce = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                accX.setText("x:"+ event.values[0]);
                accY.setText("y:"+ event.values[1]);
                accZ.setText("z:"+ event.values[2]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private SensorEventListener listenerMag = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                magX.setText("x:"+ event.values[0]);
                magY.setText("y:"+ event.values[1]);
                magZ.setText("z:"+ event.values[2]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private SensorEventListener listenerGyro = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
                gyroX.setText("x:"+ event.values[0]);
                gyroY.setText("y:"+ event.values[1]);
                gyroZ.setText("z:"+ event.values[2]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(sensorManager!= null){
            sensorManager.unregisterListener(listenerAcce);
        }
    }
}
