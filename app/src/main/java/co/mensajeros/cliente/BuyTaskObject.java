package co.mensajeros.cliente;

import java.io.Serializable;
import java.util.List;


public class BuyTaskObject implements Serializable {

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getSolicitante_nombre() {
        return solicitante_nombre;
    }

    public void setSolicitante_nombre(String solicitante_nombre) {
        this.solicitante_nombre = solicitante_nombre;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }



    String estado;
    String respuesta;
    String mensaje;
    String info;
    String description;
    String solicitante;
    String solicitante_nombre;
    String phone;
    String mobile;
    String places;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    String tipo;



    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    String tipo_pago;

    public String getIda() {
        return ida;
    }

    public void setIda(String ida) {
        this.ida = ida;
    }

    String ida;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    String result;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    String taskid;

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }



    String saldo;

    List<String> placesid=null;
    List<DetailsTaskObject> placeslist=null;

    public List getPlaceslist() {
        return placeslist;
    }

    public void setPlaceslist(List placeslist) {
        this.placeslist = placeslist;
    }



    public void setPlacesid(List placesid) {
        this.placesid = placesid;
    }

    public List getPlacesid() {
        return placesid;
    }




}
