package com.example.libb.myapplication;

import cn.bmob.v3.BmobObject;

/**
 * Created by LiBB on 2017/8/23.
 */

public class Info extends BmobObject{
    private String positionX;
    private String positionY;
    private String acceX,acceY,acceZ;
    private String magX,magY,magZ;
    private String gyroX,gyroY,gyroZ;

    public Info(){
        this.setTableName("info");
    }

    public String getPositionX() {
        return positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public String getAcceX() {
        return acceX;
    }

    public String getAcceY() {
        return acceY;
    }

    public String getAcceZ() {
        return acceZ;
    }

    public String getMagX() {
        return magX;
    }

    public String getMagY() {
        return magY;
    }

    public String getMagZ() {
        return magZ;
    }

    public String getGyroX() {
        return gyroX;
    }

    public String getGyroY() {
        return gyroY;
    }

    public String getGyroZ() {
        return gyroZ;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public void setAcceX(String acceX) {
        this.acceX = acceX;
    }

    public void setAcceY(String acceY) {
        this.acceY = acceY;
    }

    public void setAcceZ(String acceZ) {
        this.acceZ = acceZ;
    }

    public void setMagX(String magX) {
        this.magX = magX;
    }

    public void setMagY(String magY) {
        this.magY = magY;
    }

    public void setMagZ(String magZ) {
        this.magZ = magZ;
    }

    public void setGyroX(String gyroX) {
        this.gyroX = gyroX;
    }

    public void setGyroY(String gyroY) {
        this.gyroY = gyroY;
    }

    public void setGyroZ(String gyroZ) {
        this.gyroZ = gyroZ;
    }
}
