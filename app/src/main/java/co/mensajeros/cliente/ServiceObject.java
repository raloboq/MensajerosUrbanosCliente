package co.mensajeros.cliente;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rene on 9/5/14.
 */
public class ServiceObject implements Serializable {

    public ServiceObject(){
        
        
    }

    public ServiceObject(String descripcion, String valor_declarado, String ciudad, String idayVuelta, ArrayList<Direcciones> direcciones, String tipo_servicio, String fecha_recogida) {
        Descripcion = descripcion;
        Valor_declarado = valor_declarado;
        Ciudad = ciudad;
        IdayVuelta = idayVuelta;
        Direcciones = direcciones;
        Tipo_servicio = tipo_servicio;
        Fecha_recogida = fecha_recogida;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTipo_servicio() {
        return Tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        Tipo_servicio = tipo_servicio;
    }

    public String getFecha_recogida() {
        return Fecha_recogida;
    }

    public void setFecha_recogida(String fecha_recogida) {
        Fecha_recogida = fecha_recogida;
    }

    public String getValor_declarado() {
        return Valor_declarado;
    }

    public void setValor_declarado(String valor_declarado) {
        Valor_declarado = valor_declarado;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getIdayVuelta() {
        return IdayVuelta;
    }

    public void setIdayVuelta(String idayVuelta) {
        IdayVuelta = idayVuelta;
    }





    public ArrayList<Direcciones> getDirecciones() {
        return Direcciones;
    }

    public void setDirecciones(ArrayList<Direcciones> direcciones) {
        Direcciones = direcciones;
    }

    ArrayList<Direcciones>Direcciones;
    String Tipo_servicio;
    String Fecha_recogida;

    public String getHora_recogida() {
        return Hora_recogida;
    }

    public void setHora_recogida(String hora_recogida) {
        Hora_recogida = hora_recogida;
    }

    String Hora_recogida;

    String valor_servicio;
    String recargo_ida_vuelta;
    String recargo_distancia;
    String recargo_paradas;
    String valor_base;
    String paradas;
    String recargos_totales;
    String recargo_seguro;
    String ida_vuelta;
    String distancia_total;
    String valor_declarado;
    String Descripcion;
    String Valor_declarado;
    String Ciudad;
    String IdayVuelta;
    String id;

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    String fecha_creacion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor_servicio() {
        return valor_servicio;
    }

    public void setValor_servicio(String valor_servicio) {
        this.valor_servicio = valor_servicio;
    }

    public String getRecargo_ida_vuelta() {
        return recargo_ida_vuelta;
    }

    public void setRecargo_ida_vuelta(String recargo_ida_vuelta) {
        this.recargo_ida_vuelta = recargo_ida_vuelta;
    }

    public String getRecargo_distancia() {
        return recargo_distancia;
    }

    public void setRecargo_distancia(String recargo_distancia) {
        this.recargo_distancia = recargo_distancia;
    }

    public String getRecargo_paradas() {
        return recargo_paradas;
    }

    public void setRecargo_paradas(String recargo_paradas) {
        this.recargo_paradas = recargo_paradas;
    }

    public String getValor_base() {
        return valor_base;
    }

    public void setValor_base(String valor_base) {
        this.valor_base = valor_base;
    }

    public String getParadas() {
        return paradas;
    }

    public void setParadas(String paradas) {
        this.paradas = paradas;
    }

    public String getRecargos_totales() {
        return recargos_totales;
    }

    public void setRecargos_totales(String recargos_totales) {
        this.recargos_totales = recargos_totales;
    }

    public String getRecargo_seguro() {
        return recargo_seguro;
    }

    public void setRecargo_seguro(String recargo_seguro) {
        this.recargo_seguro = recargo_seguro;
    }

    public String getIda_vuelta() {
        return ida_vuelta;
    }

    public void setIda_vuelta(String ida_vuelta) {
        this.ida_vuelta = ida_vuelta;
    }

    public String getDistancia_total() {
        return distancia_total;
    }

    public void setDistancia_total(String distancia_total) {
        this.distancia_total = distancia_total;
    }
}
