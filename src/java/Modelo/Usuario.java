/** 
 *  Clase: Producto
 *  version: 1.0 
 *  Sincelejo: 20/05/2021
 *  Fecha de Modificación: 
 *  autor: Vincenzo Angelone Salgado
 */

package Modelo;

public class Usuario {
    
    private int ID_usuario;
    private int ID_rol;
    private String password;
    private String nombres;
    private String apellidos;
    private String fecha_nacimiento;
    private String genero;

    public Usuario() {}

    public Usuario(int ID_usuario, int ID_rol,String password, String Nombres, String Apellidos, String Fecha_Nacimiento, String Genero) {
        this.ID_usuario = ID_usuario;
        this.ID_rol = ID_rol;
        this.password = password;
        this.nombres = Nombres;
        this.apellidos = Apellidos;
        this.fecha_nacimiento = Fecha_Nacimiento;
        this.genero = Genero;
    }

    public int getID_usuario() {
        return ID_usuario;
    }

    public void setID_usuario(int ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public int getID_rol() {
        return ID_rol;
    }

    public void setID_rol(int ID_rol) {
        this.ID_rol = ID_rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}