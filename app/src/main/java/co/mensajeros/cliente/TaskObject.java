package co.mensajeros.cliente;

import java.io.Serializable;

/**
 * Created by rene on 11/10/14.
 */

public class TaskObject implements Serializable {




    String id;
    String phone;
    String person;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getTaskplaces() {
        return Taskplaces;
    }

    public void setTaskplaces(String taskplaces) {
        Taskplaces = taskplaces;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getDistancia() {
        return Distancia;
    }

    public void setDistancia(String distancia) {
        Distancia = distancia;
    }

    public String getSolicitante() {
        return Solicitante;
    }

    public void setSolicitante(String solicitante) {
        Solicitante = solicitante;
    }

    public String getSolicitante_nombre() {
        return Solicitante_nombre;
    }

    public void setSolicitante_nombre(String solicitante_nombre) {
        Solicitante_nombre = solicitante_nombre;
    }

    String fecha;
    String status;
    String description;
    String company;
    String places;
    String type;
    String mobile;
    String Solicitante;
    String Solicitante_nombre;
    String Valor;
    String Distancia;

    public String getHora_recogida() {
        return Hora_recogida;
    }

    public void setHora_recogida(String hora_recogida) {
        Hora_recogida = hora_recogida;
    }

    public String getFecha_recogida() {
        return Fecha_recogida;
    }

    public void setFecha_recogida(String fecha_recogida) {
        Fecha_recogida = fecha_recogida;
    }

    String Hora_recogida;
    String Fecha_recogida;

    public String getIda() {
        return Ida;
    }

    public void setIda(String ida) {
        Ida = ida;
    }

    String Ida;

    public String getComision() {
        return Comision;
    }

    public void setComision(String comision) {
        Comision = comision;
    }

    String Comision;


    String Taskplaces;
}
