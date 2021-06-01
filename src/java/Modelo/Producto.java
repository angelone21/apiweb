/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vincenzo Angelone
 */

@XmlRootElement(name = "Producto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Producto {
    
    
    public int ID_producto;
    public int ID_categoria;
    public String nombre;
    public String talla;
    public String color;
    public String marca;
    public int precio;
    public int stock;
    public String imagen;
    public float promocion;
    

    public Producto() {
    }

    public Producto(int ID_producto, int ID_categoria, String nombre, String talla, String color, String marca, int precio, int stock, String imagen, float promocion) {
        this.ID_producto = ID_producto;
        this.ID_categoria = ID_categoria;
        this.nombre = nombre;
        this.talla = talla;
        this.color = color;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.promocion = promocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getID_producto() {
        return ID_producto;
    }

    public void setID_producto(int ID_producto) {
        this.ID_producto = ID_producto;
    }

    public int getID_categoria() {
        return ID_categoria;
    }

    public void setID_categoria(int ID_categoria) {
        this.ID_categoria = ID_categoria;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public float getPromocion() {
        return promocion;
    }

    public void setPromocion(float promocion) {
        this.promocion = promocion;
    }

    
    
    
    
    
}
