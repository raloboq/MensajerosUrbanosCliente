package co.mensajeros.cliente;

import java.io.Serializable;

/**
 * Created by rene on 11/10/14.
 */

public class DetailsTaskObject implements Serializable {

    public String getDireccion()  {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    String direccion;
    String id;
    String lat;
    String longitude;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    String estado;
}
