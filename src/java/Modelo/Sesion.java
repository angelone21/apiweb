/** 
 *  Clase: Sesion
 *  version: 1.0 
 *  Sincelejo: 20/05/2021
 *  Fecha de Modificación: 
 *  autor: Osnayder Conde Rodriguez
 */

package Modelo;

public class Sesion {
    
    private String id;
    private String password;

    public Sesion() {}
    
    public Sesion(String id, String password) {
        this.id = id;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }   
}