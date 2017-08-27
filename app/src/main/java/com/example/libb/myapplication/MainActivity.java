package com.example.libb.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "SensorData";

    private SensorManager sensorManager;
    private TextView acceX,acceY,acceZ;
    private TextView magX,magY,magZ;
    private TextView gyroX,gyroY,gyroZ;
    //private Button btnStart,btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        Sensor acceSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        Sensor gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        Sensor magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        initView();
        //默认初始化
        Bmob.initialize(this, "e417769f32aabea045cbb4572a206f0f");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");
    }

    private void initView() {
        acceX = (TextView) findViewById(R.id.acceX);
        acceY = (TextView) findViewById(R.id.acceY);
        acceZ = (TextView) findViewById(R.id.acceZ);

        magX = (TextView) findViewById(R.id.magX);
        magY = (TextView) findViewById(R.id.magY);
        magZ = (TextView) findViewById(R.id.magZ);

        gyroX = (TextView) findViewById(R.id.gyroX);
        gyroY = (TextView) findViewById(R.id.gyroY);
        gyroZ = (TextView) findViewById(R.id.gyroZ);

//        btnStart = (Button) findViewById(R.id.btnStart);
//        btnStop = (Button) findViewById(R.id.btnStop);
    }

    public void startRecord(View V){
        Toast.makeText(MainActivity.this,"通过配置onClick属性实现单击监听开始记录",Toast.LENGTH_SHORT).show();

    }
    public void stopRecord(View V){
        Toast.makeText(MainActivity.this,"通过配置onClick属性实现单击监听结束记录",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 为方向传感器注册监听器
        sensorManager.registerListener(mySensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(mySensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(mySensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_UI);
    }

    Info info = new Info();



    private SensorEventListener mySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch(event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER :
                    synchronized (info){
                        acceX.setText("x:"+ event.values[0]);
                        acceY.setText("y:"+ event.values[1]);
                        acceZ.setText("z:"+ event.values[2]);
                        info.setAcceX(event.values[0]);
                        info.setAcceY(event.values[1]);
                        info.setAcceZ(event.values[2]);
                 }
                 break;
                case Sensor.TYPE_MAGNETIC_FIELD :
                    synchronized (info){
                        magX.setText("x:"+ event.values[0]);
                        magY.setText("y:"+ event.values[1]);
                        magZ.setText("z:"+ event.values[2]);
                        info.setMagX(event.values[0]);
                        info.setMagY(event.values[1]);
                        info.setMagZ(event.values[2]);

                    }
                    break;
                case Sensor.TYPE_GYROSCOPE :
                    synchronized (info){
                        gyroX.setText("x:"+ event.values[0]);
                        gyroY.setText("y:"+ event.values[1]);
                        gyroZ.setText("z:"+ event.values[2]);
                        info.setGyroX(event.values[0]);
                        info.setGyroY(event.values[1]);
                        info.setGyroZ(event.values[2]);
                        info.save(new SaveListener<String>() {
                            @Override
                            public void done(String objectId, BmobException e) {
                                if(e == null){
                                    Toast.makeText(MainActivity.this,"添加数据成功",Toast.LENGTH_SHORT).show();
                                    Log.d(TAG,"yes");
                                }else {
                                    Toast.makeText(MainActivity.this,"创建数据失败：",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
//    private SensorEventListener listenerMag = new SensorEventListener() {
//        @Override
//        public void onSensorChanged(SensorEvent event) {
//            if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
//                magX.setText("x:"+ event.values[0]);
//                magY.setText("y:"+ event.values[1]);
//                magZ.setText("z:"+ event.values[2]);
//            }
//        }
//
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//        }
//    };
//    private SensorEventListener listenerGyro = new SensorEventListener() {
//        @Override
//        public void onSensorChanged(SensorEvent event) {
//            if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
//                gyroX.setText("x:"+ event.values[0]);
//                gyroY.setText("y:"+ event.values[1]);
//                gyroZ.setText("z:"+ event.values[2]);
//            }
//        }
//
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//        }
//    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(sensorManager!= null){
            sensorManager.unregisterListener(mySensorListener);
        }
    }
}
