/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Osnayder
 */
public class OperacionesBD {

    private MySQLconnection connection;

    public OperacionesBD() {
        connection = new MySQLconnection();
    }

    public boolean login(int id, String password) {
        String sql = "SELECT * FROM usuario where ID_usuario like ? and password like ?";
        PreparedStatement statement;

        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, id + "");
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.isBeforeFirst() == true) {
                connection.close();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
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
            statement.setString(5, usuario.getApellidos());
            statement.setString(6, usuario.getFecha_nacimiento());
            statement.setString(7, usuario.getGenero());
            statement.execute();
            connection.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
        return false;
    }

    public ArrayList<Producto> listarPromociones() {
        ArrayList<Producto> listaProducto = new ArrayList();
        Producto producto;
        String sql = "SELECT * FROM producto where Promocion > 0";
        PreparedStatement statement;
        try {
            statement = connection.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                producto = new Producto();
                producto.setID_producto(result.getInt("ID_producto"));
                producto.setID_categoria(result.getInt("ID_categoria"));
                producto.setTalla(result.getString("talla"));
                producto.setMarca(result.getString("marca"));
                producto.setColor(result.getString("color"));
                producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("precio"));
                producto.setImagen(result.getString("imagen"));
                producto.setNombre(result.getString("nombre"));
                producto.setPromocion(result.getFloat("promocion"));
                producto.setPrecio(result.getInt("precio"));
                listaProducto.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
        return listaProducto;
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
        connection.close();
        return usuario;
    }
    
    public int calcularPromocion(int id) {
        String sql = "SELECT * FROM producto where ID_producto = ?";
        PreparedStatement statement;
        float descuento;
        int precio = 0;
        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
                descuento = result.getInt("precio") * result.getFloat("promocion");
                precio = ((int) (result.getInt("precio") - descuento));
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
        return precio;
    }
    
    
    

    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO producto (id,nombre,descripcion,precio,cantidad,tipo) VALUES (?, ?, ?,?,?,?)";

        PreparedStatement statement;
        try {
            statement = connection.getConnection().prepareStatement(sql);
            //statement.setInt(1, producto.getId());
            statement.execute();
            connection.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
        return false;
    }

    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre=?, descripcion=?,precio=?,cantidad=?,tipo=? WHERE id=?";

        PreparedStatement statement;
        try {
            statement = connection.getConnection().prepareStatement(sql);
            //statement.setString(1, producto.getNombre());
            statement.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM producto WHERE id=?";

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
        String sql = "SELECT * FROM producto where nombre like ?";
        PreparedStatement statement;

        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + nombre + "%");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                producto = new Producto();
                producto.setID_producto(result.getInt("ID_producto"));
                producto.setID_categoria(result.getInt("ID_categoria"));
                producto.setTalla(result.getString("talla"));
                producto.setMarca(result.getString("marca"));
                producto.setColor(result.getString("color"));
                producto.setImagen(result.getString("imagen"));
                producto.setStock(result.getInt("stock"));
                producto.setNombre(result.getString("nombre"));
                producto.setPromocion(result.getFloat("promocion"));
                producto.setPrecio(result.getInt("precio"));
                listaProducto.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
        return listaProducto;
    }

    public ArrayList<Producto> listarPorCategoria(int cat) {
        ArrayList<Producto> listaProducto = new ArrayList();
        Producto producto;
        String sql = "SELECT * FROM producto where ID_categoria = ?";
        PreparedStatement statement;
        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, cat);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                producto = new Producto();
                producto.setID_producto(result.getInt("ID_producto"));
                producto.setID_categoria(result.getInt("ID_categoria"));
                producto.setTalla(result.getString("talla"));
                producto.setMarca(result.getString("marca"));
                producto.setColor(result.getString("color"));
                producto.setImagen(result.getString("imagen"));
                producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("precio"));
                producto.setNombre(result.getString("nombre"));
                producto.setPromocion(result.getFloat("promocion"));
                producto.setPrecio(result.getInt("precio"));
                listaProducto.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
        return listaProducto;
    }

    public Producto listarPorID(int id) {
        Producto producto;
        String sql = "SELECT * FROM producto where ID_producto = ?";
        PreparedStatement statement;
        producto = new Producto();
        try {
            statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {

                producto.setID_producto(result.getInt("ID_producto"));
                producto.setID_categoria(result.getInt("ID_categoria"));
                producto.setTalla(result.getString("talla"));
                producto.setMarca(result.getString("marca"));
                producto.setColor(result.getString("color"));
                producto.setImagen(result.getString("imagen"));
                producto.setStock(result.getInt("stock"));
                producto.setPrecio(result.getInt("precio"));
                producto.setNombre(result.getString("nombre"));
                producto.setPromocion(result.getFloat("promocion"));
                producto.setPrecio(result.getInt("precio"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.close();
        return producto;
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
            //producto.setId(result.getInt("id"));

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return producto;
    }

    
    /* =====================================================================   
     *  Los Metodos creados por Osnayder estaran debajo de esta linea
     *
     */
    
    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> listaUsuarios = null;
        Usuario usuario;
        String sql = "SELECT * FROM usuario";
        PreparedStatement statement;

        try {
            statement = connection.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            listaUsuarios = new ArrayList<Usuario>();
            while (result.next()) {
                usuario = new Usuario();
                usuario.setID_usuario(result.getInt("ID_Usuario"));
                usuario.setNombres(result.getString("Nombres"));
                usuario.setApellidos(result.getString("Apellidos"));
                usuario.setGenero(result.getString("Genero"));
                usuario.setFecha_nacimiento(result.getString("Fecha_Nacimiento"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUsuarios;
    }
    
}
