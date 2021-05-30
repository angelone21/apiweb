/** 
 *  Clase: OperacionesBD 
 *  version: 1.0 
 *  Sincelejo: 20/05/2021
 *  Fecha de Modificación: 
 *  autor: Osnayder Conde Rodriguez, Vincenzo Angelone Salgado
 */

package Controlador.BD;

import Modelo.Producto;
import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OperacionesBD {

    private static MySQLconnection connection = null;

    public OperacionesBD() {
        if(OperacionesBD.connection==null){ //Singelton de conexion a Bases de datos
            connection = new MySQLconnection();
        }
    }

    public boolean login(int id, String password) {
        String sql = "SELECT * FROM usuario where ID_usuario like ? and password like ?";
        PreparedStatement statement;
        
        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + id + "%");
            statement.setString(2, "%" + password + "%");
            ResultSet result = statement.executeQuery();
            if (result.isBeforeFirst() == true) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean registroUsuarioCliente(Usuario usuario) {
        String sql = "INSERT INTO usuario (ID_usuario,ID_rol,password,nombres,apellidos,fecha_nacimiento,genero) VALUES (?, ?, ?,?,?,?,?)";
        PreparedStatement statement;
        
        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, usuario.getID_usuario());
            statement.setInt(2, usuario.getID_rol());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getNombres());
            statement.setString(5,usuario.getApellidos());
            statement.setString(6,usuario.getFecha_nacimiento());
            statement.setString(7,usuario.getGenero());
            statement.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Usuario buscarUsuario(int id) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario WHERE ID_usuario=?";
        PreparedStatement statement;

        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            usuario.setID_usuario(result.getInt("ID_usuario"));
            usuario.setID_rol(result.getInt("ID_rol"));
            usuario.setNombres(result.getString("nombres"));
            usuario.setApellidos(result.getString("apellidos"));
            usuario.setFecha_nacimiento(result.getString("fecha_nacimiento"));
            usuario.setGenero(result.getString("genero"));

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }
    

    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (id,nombre,descripcion,precio,cantidad,tipo) VALUES (?, ?, ?,?,?,?)";

        PreparedStatement statement;
        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, producto.getId());
            statement.setString(2, producto.getNombre());
            statement.setString(3, producto.getDescripcion());
            statement.setInt(4, producto.getPrecio());
            statement.setInt(5, producto.getCantidad());
            statement.setString(6, producto.getTipo());
            statement.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?,precio=?,cantidad=?,tipo=? WHERE id=?";

        PreparedStatement statement;
        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setInt(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            statement.setString(5, producto.getTipo());
            statement.setInt(6, producto.getId());
            statement.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";

        PreparedStatement statement;
        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Producto> listarProductosNombre(String nombre) {
        ArrayList<Producto> listaProducto = new ArrayList();
        Producto producto;
        String sql = "SELECT * FROM productos where nombre like ?";
        PreparedStatement statement;

        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + nombre + "%");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                producto = new Producto();
                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getInt("precio"));
                producto.setCantidad(result.getInt("cantidad"));
                producto.setDescripcion(result.getString("descripcion"));
                producto.setTipo(result.getString("tipo"));
                listaProducto.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaProducto;
    }

    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> listaProducto = new ArrayList();
        Producto producto;
        String sql = "SELECT * FROM productos";
        PreparedStatement statement;

        try {
            statement = connection.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                producto = new Producto();
                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getInt("precio"));
                producto.setCantidad(result.getInt("cantidad"));
                producto.setDescripcion(result.getString("descripcion"));
                producto.setTipo(result.getString("tipo"));
                listaProducto.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaProducto;
    }

    public Producto buscarProducto(int id) {
        Producto producto = new Producto();
        String sql = "SELECT * FROM productos WHERE id=?";
        PreparedStatement statement;

        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            producto.setId(result.getInt("id"));
            producto.setNombre(result.getString("nombre"));
            producto.setPrecio(result.getInt("precio"));
            producto.setCantidad(result.getInt("cantidad"));
            producto.setDescripcion(result.getString("descripcion"));
            producto.setTipo(result.getString("tipo"));

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return producto;
    }
}
