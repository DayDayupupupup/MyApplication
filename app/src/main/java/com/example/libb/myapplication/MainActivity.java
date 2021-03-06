package com.example.libb.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SensorData";

    private SensorManager sensorManager;
    private TextView acceX,acceY,acceZ;
    private TextView magX,magY,magZ;
    private TextView gyroX,gyroY,gyroZ;
    private EditText posX,posY,step;
    private TextView orieX,orieY,orieZ;
    private TextView setpCounter,stepDetect;
    private Boolean flag = false;
    private int mDetector = 0;



    //private Button btnStart,btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        initView();
        Bmob.initialize(this,"a76ff9c6f82ef85ad29bb8528ee90719");//默认初始化
        sensorManager.registerListener(mySensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(mySensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(mySensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(stepListener,sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(stepListener,sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR),SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(mySensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_UI);


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

        orieX = (TextView) findViewById(R.id.orieX);
        orieY = (TextView) findViewById(R.id.orieY);
        orieZ = (TextView) findViewById(R.id.orieZ);

        posX = (EditText) findViewById(R.id.posX);
        posY = (EditText) findViewById(R.id.posY);
        step = (EditText) findViewById(R.id.step);

        setpCounter = (TextView) findViewById(R.id.setpCounter);
        stepDetect = (TextView) findViewById(R.id.stepDetect);

//        btnStart = (Button) findViewById(R.id.btnStart);
//        btnStop = (Button) findViewById(R.id.btnStop);
    }

    Info info = new Info();

    public void startRecord(View V){
        flag = true;
        info.setPosX(posX.getText().toString());
        info.setPosY(posY.getText().toString());
        info.setStep(step.getText().toString());
        Log.d(TAG,"start");
        Toast.makeText(MainActivity.this,"开始上传数据",Toast.LENGTH_SHORT).show();
    }
    public void stopRecord(View V){
        flag = false;
        Log.d(TAG,"STOP");
        Toast.makeText(MainActivity.this,"停止上传数据",Toast.LENGTH_SHORT).show();
    }

    private SensorEventListener mySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch(event.sensor.getType()){
                case Sensor.TYPE_MAGNETIC_FIELD :
                    //Log.d(TAG,"TYPE");
                    synchronized (info){
                        //Log.d(TAG,"WHY");
                        magX.setText("x:"+ event.values[0]);
                        magY.setText("y:"+ event.values[1]);
                        magZ.setText("z:"+ event.values[2]);
                        //Log.d(TAG,"MAG"+event.values[0]);
                        info.setMagX(event.values[0]);
                        info.setMagY(event.values[1]);
                        info.setMagZ(event.values[2]);}
                    break;
                case Sensor.TYPE_ACCELEROMETER :
                    synchronized (info){
                        acceX.setText("x:"+ event.values[0]);
                        acceY.setText("y:"+ event.values[1]);
                        acceZ.setText("z:"+ event.values[2]);
                        //Log.d(TAG,"ACCE"+event.values[0]);
                        info.setAcceX(event.values[0]);
                        info.setAcceY(event.values[1]);
                        info.setAcceZ(event.values[2]);}
                 break;
                case Sensor.TYPE_ORIENTATION :
                    synchronized (info){
                        orieX.setText("x:"+ event.values[0]);
                        orieY.setText("y:"+ event.values[1]);
                        orieZ.setText("z:"+ event.values[2]);
                        info.setOrieX(event.values[0]);
                        info.setOrieY(event.values[1]);
                        info.setOrieZ(event.values[2]);}
                    break;
                case Sensor.TYPE_GYROSCOPE :
                    synchronized (info){
                        gyroX.setText("x:"+ event.values[0]);
                        gyroY.setText("y:"+ event.values[1]);
                        gyroZ.setText("z:"+ event.values[2]);
                        info.setGyroX(event.values[0]);
                        info.setGyroY(event.values[1]);
                        info.setGyroZ(event.values[2]);
                        if(flag == true){
                            info.save(new SaveListener<String>() {
                                @Override
                                public void done(String objectId, BmobException e) {
                                    if(e == null){
                                        //Toast.makeText(MainActivity.this,"添加数据成功",Toast.LENGTH_SHORT).show();
                                    }else {
//                                        Toast.makeText(MainActivity.this,"创建数据失败：",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                    break;
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener stepListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

                //event.values[0]为计步历史累加值

                setpCounter.setText("步数："+event.values[0] + "步");

            }

            if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {

                if (event.values[0] == 1.0) {

                    mDetector++;

                    //event.values[0]一次有效计步数据

                    stepDetect.setText("有效步数"+ mDetector + "步");
                    info.setStep_auto(mDetector);
                    if(flag == true){
                        info.save(new SaveListener<String>() {
                            @Override
                            public void done(String objectId, BmobException e) {
                                if(e == null){
                                    //Toast.makeText(MainActivity.this,"添加数据成功",Toast.LENGTH_SHORT).show();
                                }else {
//                                        Toast.makeText(MainActivity.this,"创建数据失败：",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }

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
            sensorManager.unregisterListener(mySensorListener);
        }
    }


}
