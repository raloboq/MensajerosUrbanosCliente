package co.mensajeros.cliente;

import java.io.Serializable;

/**
 * Created by Rene on 9/9/14.
 */
public class Direcciones implements Serializable{



    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    double longitude;

    public Direcciones(double longitude,double latitude, String address,boolean check) {
        this.longitude = longitude;
        Address = address;
        this.latitude = latitude;
        this.approved=check;
    }

    String Address;
    double latitude;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    boolean approved;

}
