package Controlador.BD;

import Modelo.Categoria;
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
        
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, id + "");
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.isBeforeFirst() == true) {
                connection.close();
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
        String sql = "SELECT * FROM usuario WHERE ID_Usuario=?";
        Usuario usuario;
        
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            
            usuario = new Usuario();
            usuario.setID_usuario(result.getInt("ID_Usuario"));
            usuario.setID_rol(result.getInt("ID_Rol"));
            usuario.setPassword(result.getString("Password"));
            usuario.setNombres(result.getString("Nombres"));
            usuario.setApellidos(result.getString("Apellidos"));
            usuario.setFecha_nacimiento(result.getString("Fecha_Nacimiento"));
            usuario.setGenero(result.getString("Genero"));
            connection.close();
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
        String sql = "INSERT INTO producto (ID_Producto,ID_Categoria,Talla,Color,Marca,Precio,Stock,Imagen,Promocion,nombre) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1,producto.getID_producto());
            statement.setInt(2,producto.getID_categoria());
            statement.setString(3,producto.getTalla());
            statement.setString(4,producto.getColor());
            statement.setString(5,producto.getMarca());
            statement.setInt(6,producto.getPrecio());
            statement.setInt(7,producto.getStock());
            statement.setString(8,producto.getImagen());
            statement.setFloat(9,producto.getPromocion());
            statement.setString(10,producto.getNombre());
            statement.execute();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
    
    public ArrayList<Producto> listarProductos() {
        String sql = "SELECT * FROM producto";
        ArrayList<Producto> listaProductos = null;
        Producto producto;
        
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            listaProductos = new ArrayList<Producto>();
            while (result.next()) {
                producto = new Producto();
                producto.setID_producto(result.getInt("ID_Producto"));
                producto.setID_categoria(result.getInt("ID_Categoria"));
                producto.setTalla(result.getString("Talla"));
                producto.setColor(result.getString("Color"));
                producto.setMarca(result.getString("Marca"));
                producto.setPrecio(result.getInt("Precio"));
                producto.setStock(result.getInt("Stock"));
                producto.setImagen(result.getString("Imagen"));
                producto.setPromocion(result.getInt("Promocion"));
                producto.setNombre(result.getString("nombre"));
                listaProductos.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProductos;
    }
    
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
    
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE ID_Usuario=?";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET Nombres=?, Apellidos=?,Fecha_Nacimiento=?,Genero=?,Password=? WHERE ID_Usuario=?";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1,usuario.getNombres());
            statement.setString(2,usuario.getApellidos());
            statement.setString(3,usuario.getFecha_nacimiento());
            statement.setString(4,usuario.getGenero());
            statement.setString(5,usuario.getPassword());
            statement.setInt(6,usuario.getID_usuario());
            statement.execute();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean crearCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (ID_Categoria,categoria) VALUES (?,?)";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1,categoria.getID_Categoria());
            statement.setString(2,categoria.getCategoria());
            statement.execute();
            connection.close();
            return true; 
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList<Categoria> listarCategoria() {
        String sql = "SELECT * FROM  categoria";
        ArrayList<Categoria> listaCategorias = null;
        Categoria categoria;
        
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            listaCategorias = new ArrayList<Categoria>();
            while (result.next()) {
                categoria = new Categoria();
                categoria.setID_Categoria(result.getInt("ID_Categoria"));
                categoria.setCategoria(result.getString("categoria"));
                listaCategorias.add(categoria);
            }
            return listaCategorias;
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
