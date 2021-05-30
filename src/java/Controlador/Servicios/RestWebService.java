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
import java.util.ArrayList;
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
    
    @POST
    @Path("/productos/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarProducto(Producto producto) {
        OperacionesBD operacion = new OperacionesBD();
        if (operacion.agregarProducto(producto) == true) {
            return Response.ok().build();
        }
        else{
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/capstore/registroUsuarioCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registroUsuarioCliente(Usuario usuario) {
        OperacionesBD operacion = new OperacionesBD();
        if (operacion.registroUsuarioCliente(usuario) == true) {
            System.out.println("Un usuario registrado");
            return Response.ok().build();
        }else{
            System.out.println("Un usuario llego pero no fue registrado");
            return Response.serverError().build();
        }
    }
    
    @GET
    @Path("/capstore/buscarUsuario")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario buscarUsuarioID(@QueryParam("id") String id) {
        OperacionesBD operacion = new OperacionesBD();
        Usuario usu = operacion.buscarUsuario(Integer.parseInt(id));
        return usu;
    }

    @PUT
    @Path("/productos/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(Producto producto) {
        OperacionesBD operacion = new OperacionesBD();
        if (operacion.actualizarProducto(producto) == true) {
            return Response.ok().build();
        }
        else{
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/productos/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(Producto producto) {
        OperacionesBD operacion = new OperacionesBD();
        if (operacion.eliminarProducto(producto.getId()) == true) {
            return Response.ok().build();
        }
        else{
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

    @GET
    @Path("/productos/listarPorNombre")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Producto> listarProductosNombre(@QueryParam("nombre") String nombre) {
        OperacionesBD operacion = new OperacionesBD();
        ArrayList<Producto> lista = operacion.listarProductosNombre(nombre);
        return lista;
    }

    @GET
    @Path("/productos/listar")
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<Producto> listarProductos() {
        OperacionesBD operacion = new OperacionesBD();
        ArrayList<Producto> lista = operacion.listarProductos();
        return lista;
    }

    
    @POST
    @Path("/capstore/logins")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response logins(@Context HttpServletRequest request, Sesion sesion) {
        OperacionesBD operacion = new OperacionesBD();
        
        if(operacion.login(Integer.parseInt(sesion.getId()),sesion.getPassword())){
            HttpSession misession = request.getSession(true);
            misession.setAttribute("Datos",sesion);
            return Response.ok(misession.getId(),MediaType.TEXT_PLAIN).build();
        }else{
            return Response.serverError().build();
        }
    }
    
}