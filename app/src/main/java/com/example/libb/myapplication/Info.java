package com.example.libb.myapplication;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by LiBB on 2017/8/23.
 */

public class Info extends BmobObject{
    public String posX;
    public String posY;
    public Float acceX;
    public Float acceY;
    public Float acceZ;
    public Float magX;
    public Float magY;
    public Float magZ;
    public Float gyroX;
    public Float gyroY;
    public Float gyroZ;

//    public Info(){
//        this.setTableName("info");
//    }


    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public Float getAcceX() {
        return acceX;
    }

    public void setAcceX(Float acceX) {
        this.acceX = acceX;
    }

    public Float getAcceY() {
        return acceY;
    }

    public void setAcceY(Float acceY) {
        this.acceY = acceY;
    }

    public Float getAcceZ() {
        return acceZ;
    }

    public void setAcceZ(Float acceZ) {
        this.acceZ = acceZ;
    }

    public Float getMagX() {
        return magX;
    }

    public void setMagX(Float magX) {
        this.magX = magX;
    }

    public Float getMagY() {
        return magY;
    }

    public void setMagY(Float magY) {
        this.magY = magY;
    }

    public Float getMagZ() {
        return magZ;
    }

    public void setMagZ(Float magZ) {
        this.magZ = magZ;
    }

    public Float getGyroX() {
        return gyroX;
    }

    public void setGyroX(Float gyroX) {
        this.gyroX = gyroX;
    }

    public Float getGyroY() {
        return gyroY;
    }

    public void setGyroY(Float gyroY) {
        this.gyroY = gyroY;
    }

    public Float getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(Float gyroZ) {
        this.gyroZ = gyroZ;
    }
}
