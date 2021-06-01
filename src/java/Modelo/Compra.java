/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Osnayder
 */
public class Compra {
    
    private int ID_Compra;
    private int ID_Producto;
    private int ID_Usuario;
    private float Total;
    private String Fecha;
    private float Descuento;

    public Compra() {}

    public Compra(int ID_Compra, int ID_Producto, int ID_Usuario, float Total, String Fecha, float Descuento) {
        this.ID_Compra = ID_Compra;
        this.ID_Producto = ID_Producto;
        this.ID_Usuario = ID_Usuario;
        this.Total = Total;
        this.Fecha = Fecha;
        this.Descuento = Descuento;
    }

    public float getDescuento() {
        return Descuento;
    }

    public void setDescuento(float Descuento) {
        this.Descuento = Descuento;
    }

    public int getID_Compra() {
        return ID_Compra;
    }

    public void setID_Compra(int ID_Compra) {
        this.ID_Compra = ID_Compra;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
}
