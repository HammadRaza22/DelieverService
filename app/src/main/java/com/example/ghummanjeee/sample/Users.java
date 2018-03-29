package com.example.ghummanjeee.sample;

/**
 * Created by GhummanJeee on 3/16/2018.
 */

public class Users {
    private  String FName;
    public String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
/*public Users(String FName, String LName, String EName, String passName, String ph, String address) {
        this.FName = FName;
        this.LName = LName;
        this.EName = EName;
        PassName = passName;
        Ph = ph;
        this.address = address;
    }*/

    private  String LName;
    private  String EName;
    private  String PassName;
    private  String Ph;
    private  String address;

    public String getPassName() {
        return PassName;
    }

    public void setPassName(String passName) {
        PassName = passName;
    }

    public String getPh() {
        return Ph;
    }

    public void setPh(String ph) {
        Ph = ph;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEName(String s) {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }



}
