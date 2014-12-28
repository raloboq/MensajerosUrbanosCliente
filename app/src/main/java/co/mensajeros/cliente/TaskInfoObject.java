package co.mensajeros.cliente;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rene on 11/12/14.
 */
public class TaskInfoObject implements Serializable {

    public String getNombre_mensajero() {
        return nombre_mensajero;
    }

    public void setNombre_mensajero(String nombre_mensajero) {
        this.nombre_mensajero = nombre_mensajero;
    }

    public String getCelular_mensajero() {
        return celular_mensajero;
    }

    public void setCelular_mensajero(String celular_mensajero) {
        this.celular_mensajero = celular_mensajero;
    }

    public String getCedula_mensajero() {
        return cedula_mensajero;
    }

    public void setCedula_mensajero(String cedula_mensajero) {
        this.cedula_mensajero = cedula_mensajero;
    }

    public String getPlaca_mensajero() {
        return placa_mensajero;
    }

    public void setPlaca_mensajero(String placa_mensajero) {
        this.placa_mensajero = placa_mensajero;
    }

    public String getEstado_task() {
        return estado_task;
    }

    public void setEstado_task(String estado_task) {
        this.estado_task = estado_task;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_asignado() {
        return fecha_asignado;
    }

    public void setFecha_asignado(String fecha_asignado) {
        this.fecha_asignado = fecha_asignado;
    }

    public String getFecha_finalizado() {
        return fecha_finalizado;
    }

    public void setFecha_finalizado(String fecha_finalizado) {
        this.fecha_finalizado = fecha_finalizado;
    }

    public List<DetailsTaskObject> getAddress() {
        return address;
    }

    public void setAddress(List<DetailsTaskObject> address) {
        this.address = address;
    }

    String nombre_mensajero;
    String celular_mensajero;
    String cedula_mensajero;
    String placa_mensajero;
    String estado_task;
    String fecha_creacion;
    String fecha_asignado;
    String fecha_finalizado;
    List<DetailsTaskObject> address;
    String mensaje;
    String id;

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getValor_total() {
        return valor_total;
    }

    public void setValor_total(String valor_total) {
        this.valor_total = valor_total;
    }

    String distancia;
    String valor_total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
