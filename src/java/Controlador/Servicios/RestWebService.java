/** 
 *  Clase: Producto
 *  version: 1.0 
 *  Sincelejo: 20/05/2021
 *  Fecha de Modificación: 
 *  autor: Osnayder Conde Rodriguez, Vincenzo Angelone Salgado
 */

package Controlador.Servicios;

import Controlador.BD.OperacionesBD;
import Modelo.Producto;
import Modelo.Sesion;
import Modelo.Usuario;
import com.mysql.cj.xdevapi.JsonArray;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Stateless
@Path("/services")
public class RestWebService {
 
    @GET
    @Path("/capstore/login")
    public boolean login(@QueryParam("id") String id, @QueryParam("p") String password) {
        OperacionesBD operacion = new OperacionesBD();
        return operacion.login(Integer.parseInt(id), password);
    }

    @GET
    @Path("/capstore/listarPromociones")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Producto> listarPromociones() {
        OperacionesBD operacion = new OperacionesBD();
        ArrayList<Producto> lista = operacion.listarPromociones();
        return lista;
    }

    @GET
    @Path("/capstore/buscarUsuario")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario buscarUsuarioID(@QueryParam("id") String id) {
        OperacionesBD operacion = new OperacionesBD();
        Usuario usu = operacion.buscarUsuario(Integer.parseInt(id));
        return usu;
    }
    
    @GET
    @Path("/capstore/listarPorNombre")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Producto> listarProductosNombre(@QueryParam("nombre") String nombre) {
        OperacionesBD operacion = new OperacionesBD();
        ArrayList<Producto> lista = operacion.listarProductosNombre(nombre);
        return lista;
    }
    
    @GET
    @Path("/capstore/listarPorCategoria")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Producto> listarPorCategoria(@QueryParam("categoria") int cat) {
        OperacionesBD operacion = new OperacionesBD();
        ArrayList<Producto> lista = operacion.listarPorCategoria(cat);
        return lista;
    }
    
    @GET
    @Path("/capstore/listarPorID")
    @Produces({MediaType.APPLICATION_JSON})
    public Producto listarPorID(@QueryParam("id") int id) {
        OperacionesBD operacion = new OperacionesBD();
        Producto pro = operacion.listarPorID(id);
        return pro;
    }
    
    @GET
    @Path("/capstore/calulcarPromocion")
    @Produces({MediaType.TEXT_PLAIN})
    public int calcularPromocion(@QueryParam("id") int id) {
        OperacionesBD operacion = new OperacionesBD();
        return operacion.calcularPromocion(id);
    }
    
    
    @POST
    @Path("/capstore/registroUsuarioCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registroUsuarioCliente(Usuario Usuario) {
        OperacionesBD operacion = new OperacionesBD();
        if (operacion.registroUsuarioCliente(Usuario) == true) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }

    }

    @POST
    @Path("/productos/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarProducto(Producto producto) {
        OperacionesBD operacion = new OperacionesBD();
        if (operacion.agregarProducto(producto) == true) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }

    }


    @PUT
    @Path("/productos/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(Producto producto) {
        OperacionesBD operacion = new OperacionesBD();
        if (operacion.actualizarProducto(producto) == true) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/productos/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(Producto producto) {
        OperacionesBD operacion = new OperacionesBD();
        if (operacion.eliminarProducto(producto.getID_producto()) == true) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("/productos/buscar")
    @Produces({MediaType.APPLICATION_JSON})
    public Producto buscarProductoID(@QueryParam("id") String id) {
        OperacionesBD operacion = new OperacionesBD();
        Producto pro = operacion.buscarProducto(Integer.parseInt(id));
        return pro;
    }

    /* =====================================================================   
     *  Los Metodos creados por Osnayder estaran debajo de esta linea
     *
     */
    @POST
    @Path("/capstore/sesion")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response iniciarSesion(@Context HttpServletRequest request, Sesion sesion) {
        OperacionesBD operacion = new OperacionesBD();
        
        if(operacion.login(Integer.parseInt(sesion.getId()),sesion.getPassword())){
            HttpSession misession = request.getSession(true);
            misession.setAttribute("Datos",sesion);
            return Response.ok(misession.getId(),MediaType.TEXT_PLAIN).build();
        }else{
            return Response.ok("ERRORX1",MediaType.TEXT_PLAIN).build();
        }
    }
    
    @GET
    @Path("/capstore/listarusuarios")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarUsuarios() {
        OperacionesBD operacion = new OperacionesBD();
        List<Usuario> listaUsuarios = operacion.listarUsuarios();
        
        if(listaUsuarios!=null){
            GenericEntity<List<Usuario>> entidad = new GenericEntity<List<Usuario>>(listaUsuarios){};
            return Response.ok(entidad).build();
        }else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}